package cz.cvut.fel.pc2e.garminworker.services;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import cz.cvut.fel.pc2e.garminworker.repository.DeviceDao;
import cz.cvut.fel.pc2e.garminworker.model.dto.UserIdReplyDto;
import cz.cvut.fel.pc2e.garminworker.model.entities.DeviceEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

@Service
@Slf4j
public class DeviceService {
    private final DeviceDao dao;
    private final OauthService oauthService;

    @Autowired
    public DeviceService(DeviceDao dao, OauthService oauthService) {
        this.dao = dao;
        this.oauthService = oauthService;
    }

    /**
     * Find all research numbers (device_id) for a user with oauth token
     * @return null if entities no research number (device_id) was found
     */
    public String getAllResearchIdsByOauthToken(String oauthToken) {
        List<DeviceEntity> entities = dao.findDeviceEntitiesByUserAccessToken(oauthToken);
        if (entities.isEmpty()) return null;
        return connectDeviceIdsInString(entities);
    }

    /**
     * Find all research numbers (device_id) for a user with user id
     * @return null if entities no research number (device_id) was found
     */
    public String getAllResearchIdsByUserId(String userId) {
        List<DeviceEntity> entities = dao.findDeviceEntitiesByUserId(userId);
        if (entities.isEmpty()) return null;
        return connectDeviceIdsInString(entities);
    }

    /**
     * Finds all device entities in database and if found device hasn't got a userId yet, it finds it and updates that entity
     */
    @Transactional
    public void connectExistingDevicesToUserIds() {
        List<DeviceEntity> entities = dao.findAll();
        for (DeviceEntity e : entities) {
            if (e.getUserId() != null) {
                // device already has userId, skipping
                log.debug("Device with researchNumeber {} already has userId: {} skipping", e.getResearchNumber(), e.getUserId());
                continue;
            }

            String OAUTH_TOKEN = e.getUserAccessToken();
            String OAUTH_TOKEN_SECRET = e.getOauthTokenSecret();

            log.debug("Entity with OAUTH_TOKEN {} OAUTH_TOKEN_SECRET {}", OAUTH_TOKEN, OAUTH_TOKEN_SECRET);

            String userId = getUserId(OAUTH_TOKEN, OAUTH_TOKEN_SECRET);
            if (userId == null) {
                log.debug("Found no userId");
                continue;
            }
            log.debug("Found userId {}, research number {}", userId, e.getResearchNumber());
            dao.updateUserId(e.getId(), userId);
        }
    }

    /**
     * Retrive userId at Garmin endpoint by OAUTH_TOKEN and OAUTH_TOKEN_SECRET
     * @param OAUTH_TOKEN is user specific OAUTH_TOKEN
     * @param OAUTH_TOKEN_SECRET is user specific OAUTH_TOKEN_SECRET
     * @return userId as a String if userId was found, null otherwise
     */
    public String getUserId(String OAUTH_TOKEN, String OAUTH_TOKEN_SECRET) {
        try {
            // garmin endpoint to get user id
            String uri = "https://apis.garmin.com/wellness-api/rest/user/id";

            String authorization = oauthService.getOauth1Header(OAUTH_TOKEN, OAUTH_TOKEN_SECRET, uri);
            HttpClient client = HttpClient.newHttpClient();

            // Create HTTP request object
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://apis.garmin.com/wellness-api/rest/user/id"))
                    .GET()
                    .header("Authorization", authorization)
                    .header("Content-Type", "application/json")
                    .build();
            // Send HTTP request
            HttpResponse<String> response = client.send(request,
                    HttpResponse.BodyHandlers.ofString());

            ObjectMapper om = new ObjectMapper();
            om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            UserIdReplyDto userIdReplyDto = om.readValue(response.body(), UserIdReplyDto.class);

            if (userIdReplyDto.getUserId() != null) {
                return userIdReplyDto.getUserId();
            } else if(userIdReplyDto.getErrorMessage() != null) {
                log.debug("Get user id response error message: {} for OAUTH_TOKEN {}", userIdReplyDto.getErrorMessage(), OAUTH_TOKEN);
                return null;
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        log.error("Could not find user id for token ${} and token secret ${}", OAUTH_TOKEN, OAUTH_TOKEN_SECRET);
        return null;
    }

    /**
     * This method returns a string representation of all the research numbers (device_id) of given list of entities
     * @return string such as "932-o21, 342-234" in case there are two entities or "932-o21" if there is just one
     */
    private String connectDeviceIdsInString(List<DeviceEntity> entities) {
        if (entities.isEmpty()) return null;
        StringBuilder res = new StringBuilder("");
        for (DeviceEntity e : entities) {
            res
                    .append(e.getResearchNumber())
                    .append(", ");
        }
        // get id of last ', ' characters
        if (entities.size() > 0) {
            res.setLength(res.length() - 2);
        }
        return res.toString();
    }
}
