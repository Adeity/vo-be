package cz.cvut.fel.pc2e.garminworker.cloud.api.controllers;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import cz.cvut.fel.pc2e.garminworker.cloud.api.dto.ActivityIntensityEnum;
import cz.cvut.fel.pc2e.garminworker.cloud.api.dto.EpochSummaryDto;
import cz.cvut.fel.pc2e.garminworker.cloud.api.dto.EpochsPushNotificationDto;
import cz.cvut.fel.pc2e.garminworker.entities.DeviceEntity;
import cz.cvut.fel.pc2e.garminworker.entities.PeriodOffsetEntity;
import cz.cvut.fel.pc2e.garminworker.kafka.messages.ActivityMessage;
import cz.cvut.fel.pc2e.garminworker.kafka.producers.ActivityMessageProducer;
import cz.cvut.fel.pc2e.garminworker.kafka.producers.RawMessageProducer;
import cz.cvut.fel.pc2e.garminworker.repositories.DeviceRepository;
import cz.cvut.fel.pc2e.garminworker.repositories.PeriodOffsetRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping(value = "/garmin/epochs")
public class HealthEpochController {

    private static final int EPOCH_DURATION_SECONDS = 900;
    private static final int EPOCH_DURATION_MINUTES = 15;
    private static final int SEDENTARY_INTENSITY_NUMBER = 0;
    private static final int ACTIVE_INTENSITY_NUMBER = 50;
    private static final int HIGHLY_ACTIVE_INTENSITY_NUMBER = 100;

    private final RawMessageProducer rawMessageProducer;

    private final ActivityMessageProducer activityMessageProducer;

    private final DeviceRepository deviceRepository;

    private final PeriodOffsetRepository periodOffsetRepository;

    public HealthEpochController(RawMessageProducer rawMessageProducer, ActivityMessageProducer activityMessageProducer, DeviceRepository deviceRepository, PeriodOffsetRepository periodOffsetRepository) {
        this.rawMessageProducer = rawMessageProducer;
        this.activityMessageProducer = activityMessageProducer;
        this.deviceRepository = deviceRepository;
        this.periodOffsetRepository = periodOffsetRepository;
    }

    @PostMapping
    public ResponseEntity<Serializable> postData(@RequestBody String pBody) {
        log.info("Received push notification for EPOCHS");

        rawMessageProducer.sendRawMessage(pBody);

        try {
            ObjectMapper om = new ObjectMapper();
            om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            EpochsPushNotificationDto epochsPushNotificationDto = om.readValue(pBody, EpochsPushNotificationDto.class);

            if (epochsPushNotificationDto != null && epochsPushNotificationDto.getEpochs() != null) {

                for (EpochSummaryDto epochSummaryDto : epochsPushNotificationDto.getEpochs()) {
                    processEpochSummary(epochSummaryDto);
                }
            }
        } catch (Exception e) {
            log.error("Exception occurred during processing of EPOCH message!", e);
            rawMessageProducer.sendRawDltMessage(pBody);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    private void processEpochSummary(EpochSummaryDto epochSummaryDto) {
        log.debug("Processing epochSummaryDto: {} ", epochSummaryDto);

        if (epochSummaryDto.getUserAccessToken() == null) {
            log.warn("Skipping epoch, because userAccessToken is missing. epoch: {}", epochSummaryDto);
            return;
        }

        if (epochSummaryDto.getDurationInSeconds() < EPOCH_DURATION_SECONDS) {
            log.debug("Skipping epoch summaryId: {} because it is shorter then standard epoch duration. Actual size: {}", epochSummaryDto.getSummaryId(), epochSummaryDto.getDurationInSeconds());
            return;
        }

        Optional<DeviceEntity> deviceEntityOpt = deviceRepository.findByOauthToken(epochSummaryDto.getUserAccessToken());

        deviceEntityOpt.ifPresentOrElse(deviceEntity -> {
            String deviceId = deviceEntity.getDeviceId();

            int activityDuration = epochSummaryDto.getActiveTimeInSeconds();
            long startTime = epochSummaryDto.getStartTimeInSeconds();

            PeriodOffsetEntity periodOffsetEntity = getOffsetInEpoch(epochSummaryDto, deviceEntity.getId());
            int offsetInEpoch = periodOffsetEntity.getOffsetInPeriod();
            int durationInMinutes = getDurationInMinutes(activityDuration);

            // adjust duration to the 15min window
            while (durationInMinutes * 60 + offsetInEpoch > EPOCH_DURATION_SECONDS) {
                durationInMinutes--;
                activityDuration -= 60;
            }

            int newOffset = offsetInEpoch + durationInMinutes * 60;
            // update offset for next activity in epoch
            updateOffsetInEpoch(periodOffsetEntity, newOffset);

            if (durationInMinutes >= 0 && activityDuration > 0) {
                double rawStepsPerMinute = (double) epochSummaryDto.getSteps() / durationInMinutes;
                int stepsPerMinute = (int) rawStepsPerMinute;
                double roundingError = rawStepsPerMinute - stepsPerMinute;

                double metPerMinute = epochSummaryDto.getMet() / durationInMinutes;
                int intensity = getActivityIntensityInNumber(epochSummaryDto.getIntensity());

                double currError = 0.0;
                int currStepsPerMinute;
                // for each minute send activity data to specific queues
                for (int i = 0; i < activityDuration; i += 60) {
                    if (currError >= 1.0) {
                        currError -= 1;
                        currStepsPerMinute = stepsPerMinute + 1;
                    } else {
                        currStepsPerMinute = stepsPerMinute;
                    }
                    // activity
                    ActivityMessage activityMessage = new ActivityMessage(deviceId, startTime + offsetInEpoch + i, epochSummaryDto.getActivityType(), currStepsPerMinute, intensity, metPerMinute, new Timestamp(System.currentTimeMillis()));
                    activityMessageProducer.sendActivityMessage(activityMessage);

                    currError += roundingError;
                }
            }
        }, () -> log.error("Skipping epoch, because device with oAuthToken: {} was not found", epochSummaryDto.getUserAccessToken()));
    }

    private PeriodOffsetEntity getOffsetInEpoch(EpochSummaryDto epochSummaryDto, int deviceId) {
        Timestamp startTime = new Timestamp(epochSummaryDto.getStartTimeInSeconds() * 1000);

        PeriodOffsetEntity periodOffsetEntity = new PeriodOffsetEntity();
        periodOffsetEntity.setEpochStartTime(startTime);
        periodOffsetEntity.setDeviceId(deviceId);

        // only if there is more activities within one epoch
        if (epochSummaryDto.getActiveTimeInSeconds() < EPOCH_DURATION_SECONDS) {
            Optional<PeriodOffsetEntity> periodOffsetEntityOptional = periodOffsetRepository.findByDeviceIdAndEpochStartTime(deviceId, startTime);

            if (periodOffsetEntityOptional.isPresent()) {
                periodOffsetEntity = periodOffsetEntityOptional.get();
            }
        }

        return periodOffsetEntity;
    }

    private void updateOffsetInEpoch(PeriodOffsetEntity periodOffsetEntity, int newOffset) {
        if (newOffset < EPOCH_DURATION_SECONDS) {
            periodOffsetEntity.setOffsetInPeriod(newOffset);
            periodOffsetRepository.save(periodOffsetEntity);
        } else {
            // We do not need the information anymore
            periodOffsetRepository.delete(periodOffsetEntity);
        }
    }

    private int getActivityIntensityInNumber(ActivityIntensityEnum activityIntensityEnum) {
        switch (activityIntensityEnum) {
            case SEDENTARY:
                return SEDENTARY_INTENSITY_NUMBER;
            case ACTIVE:
                return ACTIVE_INTENSITY_NUMBER;
            case HIGHLY_ACTIVE:
                return HIGHLY_ACTIVE_INTENSITY_NUMBER;
            default:
                return 0;
        }
    }

    private int getDurationInMinutes(int activityDuration) {
        int durationInMinutes = (int) Math.ceil(activityDuration / 60.0);
        if (durationInMinutes == EPOCH_DURATION_MINUTES && activityDuration < EPOCH_DURATION_SECONDS) {
            // in order not to skip activity shorter than 1 minute
            durationInMinutes--;
        }
        return durationInMinutes;
    }
}
