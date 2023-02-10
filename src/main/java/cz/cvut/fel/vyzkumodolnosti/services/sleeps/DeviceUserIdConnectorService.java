package cz.cvut.fel.vyzkumodolnosti.services.sleeps;

import cz.cvut.fel.vyzkumodolnosti.model.entities.DeviceEntity;
import cz.cvut.fel.vyzkumodolnosti.repository.forms.DeviceRepository;
import cz.cvut.fel.vyzkumodolnosti.services.DeviceGarminApiCommunicatorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@Service
public class DeviceUserIdConnectorService {
	private final DeviceGarminApiCommunicatorService deviceGarminApiCommunicatorService;
	private final DeviceRepository dao;

	@Autowired
	public DeviceUserIdConnectorService(DeviceGarminApiCommunicatorService deviceGarminApiCommunicatorService, DeviceRepository dao) {
		this.deviceGarminApiCommunicatorService = deviceGarminApiCommunicatorService;
		this.dao = dao;
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

			String userId = deviceGarminApiCommunicatorService.getUserId(OAUTH_TOKEN, OAUTH_TOKEN_SECRET);
			if (userId == null) {
				log.debug("Found no userId");
				continue;
			}
			log.debug("Found userId {}, research number {}", userId, e.getResearchNumber());
			dao.updateUserId(e.getId(), userId);
		}
	}
}