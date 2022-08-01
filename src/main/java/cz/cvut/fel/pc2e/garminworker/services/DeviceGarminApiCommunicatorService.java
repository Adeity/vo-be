package cz.cvut.fel.pc2e.garminworker.services;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import cz.cvut.fel.pc2e.garminworker.model.dto.UserIdReplyDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Slf4j
@Service
public class DeviceGarminApiCommunicatorService {
	private final OauthService oauthService;

	@Autowired
	public DeviceGarminApiCommunicatorService(OauthService oauthService) {
		this.oauthService = oauthService;
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
}