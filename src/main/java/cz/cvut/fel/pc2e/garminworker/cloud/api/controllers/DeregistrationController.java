package cz.cvut.fel.pc2e.garminworker.cloud.api.controllers;

import cz.cvut.fel.pc2e.garminworker.cloud.api.dto.DeregistrationDto;
import cz.cvut.fel.pc2e.garminworker.cloud.api.dto.DeregistrationPushNotificationDto;
import cz.cvut.fel.pc2e.garminworker.entities.DeviceEntity;
import cz.cvut.fel.pc2e.garminworker.repositories.DeviceRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.Serializable;
import java.util.Optional;

@Slf4j
@Controller
public class DeregistrationController {

    private final DeviceRepository deviceRepository;

    public DeregistrationController(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    @PostMapping("/garmin/deregistration")
    public ResponseEntity<Serializable> deregisterDevice(@RequestBody DeregistrationPushNotificationDto deregistrationPushNotificationDto) {
        log.info("Received push notification for deregistering device");


        if (deregistrationPushNotificationDto != null && deregistrationPushNotificationDto.getDeregistrations() != null) {
            for (DeregistrationDto deregistrationDto : deregistrationPushNotificationDto.getDeregistrations()) {
                try {
                    log.debug("Processing deregistrationDto: {} ", deregistrationDto);

                    if (deregistrationDto.getUserAccessToken() == null) {
                        log.warn("Skipping deregistration, because userAccessToken is missing. daily: {}", deregistrationDto);
                        continue;
                    }

                    Optional<DeviceEntity> deviceEntityOpt = deviceRepository.findByOauthToken(deregistrationDto.getUserAccessToken());

                    deviceEntityOpt.ifPresentOrElse(deviceEntity -> {
                        // since this endpoint is just to meet the requirements, we just set the allowed flag to false and log the action
                        deviceEntity.setAllowed(false);
                        deviceRepository.save(deviceEntity);
                        log.info("Successfully deregistered device with deviceId: {}", deviceEntity.getDeviceId());
                    }, () ->
                            log.error("Skipping deregistration, because device with oAuthToken: {} was not found", deregistrationDto.getUserAccessToken()));
                } catch (Exception e) {
                    log.error("Exception occurred during processing of DEREGISTRATION: {}", deregistrationDto, e);
                }
            }
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
