package cz.cvut.fel.pc2e.garminworker.controllers;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import cz.cvut.fel.pc2e.garminworker.dto.DailiesPushNotificationDto;
import cz.cvut.fel.pc2e.garminworker.dto.DailySummaryDto;
import cz.cvut.fel.pc2e.garminworker.entities.DeviceEntity;
import cz.cvut.fel.pc2e.garminworker.kafka.messages.HeartRateMessage;
import cz.cvut.fel.pc2e.garminworker.kafka.producers.HeartRateMessageProducer;
import cz.cvut.fel.pc2e.garminworker.kafka.producers.RawMessageProducer;
import cz.cvut.fel.pc2e.garminworker.dao.DeviceDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Map;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping(value = "/garmin/dailies")
public class DailiesController {
    private final RawMessageProducer rawMessageProducer;

    private final HeartRateMessageProducer heartRateMessageProducer;

    private final DeviceDao deviceRepository;

    public DailiesController(RawMessageProducer rawMessageProducer, HeartRateMessageProducer heartRateMessageProducer, DeviceDao deviceDao) {
        this.rawMessageProducer = rawMessageProducer;
        this.heartRateMessageProducer = heartRateMessageProducer;
        this.deviceRepository = deviceDao;
    }

    @PostMapping
    public ResponseEntity<Serializable> postData(@RequestBody String pBody) {
        log.info("Received push notification for DAILIES");

        rawMessageProducer.sendRawMessage(pBody);

        try {
            ObjectMapper om = new ObjectMapper();
            om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            DailiesPushNotificationDto dailiesPushNotificationDto = om.readValue(pBody, DailiesPushNotificationDto.class);

            if (dailiesPushNotificationDto != null && dailiesPushNotificationDto.getDailies() != null) {
                for (DailySummaryDto dailySummaryDto : dailiesPushNotificationDto.getDailies()) {
                    processDailySummary(dailySummaryDto);
                }
            }
        } catch (Exception e) {
            log.error("Exception occurred during processing of DAILY message", e);
            rawMessageProducer.sendRawDltMessage(pBody);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    private void processDailySummary(DailySummaryDto dailySummaryDto) {
        log.debug("Processing dailySummaryDto: {} ", dailySummaryDto);

        if (dailySummaryDto.getUserAccessToken() == null) {
            log.warn("Skipping daily summary, because userAccessToken is missing. daily: {}", dailySummaryDto);
            return;
        }

        Optional<DeviceEntity> deviceEntityOpt = deviceRepository.findByOauthToken(dailySummaryDto.getUserAccessToken());

        deviceEntityOpt.ifPresentOrElse(deviceEntity -> {
            String deviceId = deviceEntity.getDeviceId();

            long startTime = dailySummaryDto.getStartTimeInSeconds();

            // for each hear rate record
            if (dailySummaryDto.getTimeOffsetHeartRateSamples() != null) {
                for (Map.Entry<Integer, Integer> heartRateSample : dailySummaryDto.getTimeOffsetHeartRateSamples().entrySet()) {
                    long currentSampleTime = startTime + heartRateSample.getKey();

                    HeartRateMessage heartRateMessage = new HeartRateMessage(deviceId, currentSampleTime, heartRateSample.getValue(), new Timestamp(System.currentTimeMillis()));
                    heartRateMessageProducer.sendHeartRateMessage(heartRateMessage);
                }
            }
        }, () -> log.error("Skipping daily, because device with oAuthToken: {} was not found", dailySummaryDto.getUserAccessToken()));
    }
}
