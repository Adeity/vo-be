package cz.cvut.fel.pc2e.garminworker.cloud.api.controllers;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import cz.cvut.fel.pc2e.garminworker.cloud.api.dto.SleepLevelTimeRange;
import cz.cvut.fel.pc2e.garminworker.cloud.api.dto.SleepSummaryDto;
import cz.cvut.fel.pc2e.garminworker.cloud.api.dto.SleepsPushNotificationDto;
import cz.cvut.fel.pc2e.garminworker.cloud.api.dto.ValidationTypeEnum;
import cz.cvut.fel.pc2e.garminworker.entities.DeviceEntity;
import cz.cvut.fel.pc2e.garminworker.kafka.messages.SleepLevelTimeRangeMessage;
import cz.cvut.fel.pc2e.garminworker.kafka.messages.SleepLevelTypeEnum;
import cz.cvut.fel.pc2e.garminworker.kafka.messages.SleepSummaryMessage;
import cz.cvut.fel.pc2e.garminworker.kafka.producers.RawMessageProducer;
import cz.cvut.fel.pc2e.garminworker.kafka.producers.SleepMessageProducer;
import cz.cvut.fel.pc2e.garminworker.repositories.DeviceRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping(value = "/garmin/sleeps")
public class SleepsController {

    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    private final RawMessageProducer rawMessageProducer;

    private final SleepMessageProducer sleepMessageProducer;

    private final DeviceRepository deviceRepository;

    public SleepsController(RawMessageProducer rawMessageProducer, SleepMessageProducer sleepMessageProducer, DeviceRepository deviceRepository) {
        this.rawMessageProducer = rawMessageProducer;
        this.sleepMessageProducer = sleepMessageProducer;
        this.deviceRepository = deviceRepository;
    }

    @PostMapping
    public ResponseEntity<Serializable> postData(@RequestBody String pBody) {
        log.info("Received push notification for SLEEPS");

        rawMessageProducer.sendRawMessage(pBody);

        try {
            ObjectMapper om = new ObjectMapper();
            om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            SleepsPushNotificationDto sleepsPushNotificationDto = om.readValue(pBody, SleepsPushNotificationDto.class);

            if (sleepsPushNotificationDto != null && sleepsPushNotificationDto.getSleeps() != null) {
                for (SleepSummaryDto sleepSummaryDto : sleepsPushNotificationDto.getSleeps()) {
                    processSleepSummary(sleepSummaryDto);
                }
            }
        } catch (Exception e) {
            log.error("Exception occurred during processing of SLEEP message", e);
            rawMessageProducer.sendRawDltMessage(pBody);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    private void processSleepSummary(SleepSummaryDto sleepSummaryDto) {
        log.debug("Processing sleepSummaryDto: {} ", sleepSummaryDto);

        // we accept only enhanced records, because all of our devices support enhanced mode with REM sleep
        // also we accept TENTATIVE mode, because the API is unpredictable, and it is quite often to receive just TENTATIVE validation state and no FINAL,
        // so we need to have server-side logic in aggregator to be able to update records in case of update
        if (ValidationTypeEnum.ENHANCED_TENTATIVE.equals(sleepSummaryDto.getValidation()) ||
                ValidationTypeEnum.ENHANCED_FINAL.equals(sleepSummaryDto.getValidation())) {
            if (sleepSummaryDto.getUserAccessToken() == null) {
                log.warn("Skipping sleep summary, because userAccessToken is missing. sleep: {}", sleepSummaryDto);
                return;
            }
            if (sleepSummaryDto.getStartTimeInSeconds() == null || sleepSummaryDto.getDurationInSeconds() == null) {
                log.warn("Skipping sleep summary, because startTime or duration is missing. sleep: {}", sleepSummaryDto);
                return;
            }

            Optional<DeviceEntity> deviceEntityOpt = deviceRepository.findByOauthToken(sleepSummaryDto.getUserAccessToken());

            deviceEntityOpt.ifPresentOrElse(deviceEntity -> {
                String deviceId = deviceEntity.getDeviceId();

                Long startTime = sleepSummaryDto.getStartTimeInSeconds();
                String calendarDate = getCalendarDate(sleepSummaryDto.getCalendarDate(), sleepSummaryDto.getStartTimeInSeconds());

                SleepSummaryMessage sleepSummaryMessage = new SleepSummaryMessage(deviceId, new Timestamp(System.currentTimeMillis()), calendarDate, startTime,
                        startTime + sleepSummaryDto.getDurationInSeconds(), sleepSummaryDto.getDurationInSeconds(), sleepSummaryDto.getUnmeasurableSleepInSeconds(),
                        sleepSummaryDto.getDeepSleepDurationInSeconds(), sleepSummaryDto.getLightSleepDurationInSeconds(), sleepSummaryDto.getRemSleepInSeconds(),
                        sleepSummaryDto.getAwakeDurationInSeconds());
                sleepMessageProducer.sendSleepSummaryMessage(sleepSummaryMessage);

                processSleepLevelsMap(sleepSummaryDto.getSleepLevelsMap(), deviceId, calendarDate);

            }, () -> log.error("Skipping sleep, because device with oAuthToken: {} was not found", sleepSummaryDto.getUserAccessToken()));
        } else {
            log.warn("Skipping sleep summary with ID: {}, because of ValidationType: {}", sleepSummaryDto.getSummaryId(), sleepSummaryDto.getValidation());
        }
    }

    private void processSleepLevelsMap(Map<String, List<SleepLevelTimeRange>> sleepLevelsMap, String deviceId, String calendarDate) {
        if (sleepLevelsMap != null) {
            for (Map.Entry<String, List<SleepLevelTimeRange>> sleepLevelTimeRangeEntry : sleepLevelsMap.entrySet()) {
                if (!CollectionUtils.isEmpty(sleepLevelTimeRangeEntry.getValue())) {
                    for (SleepLevelTimeRange sleepLevelTimeRange : sleepLevelTimeRangeEntry.getValue()) {
                        SleepLevelTimeRangeMessage sleepLevelTimeRangeMessage = new SleepLevelTimeRangeMessage(deviceId, new Timestamp(System.currentTimeMillis()), calendarDate,
                                sleepLevelTimeRange.getStartTimeInSeconds(), sleepLevelTimeRange.getEndTimeInSeconds(), SleepLevelTypeEnum.valueOf(sleepLevelTimeRangeEntry.getKey().toUpperCase()));
                        sleepMessageProducer.sendSleepLevelTimeRangeMessage(sleepLevelTimeRangeMessage);
                    }
                }
            }
        }
    }

    private String getCalendarDate(String calendarDate, Long startTime) {
        if (StringUtils.isNotEmpty(calendarDate)) {
            return calendarDate;
        }

        Timestamp timestamp = new Timestamp(startTime * 1000);
        return sdf.format(timestamp);
    }
}
