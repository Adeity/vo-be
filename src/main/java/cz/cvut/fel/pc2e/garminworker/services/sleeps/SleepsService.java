package cz.cvut.fel.pc2e.garminworker.services.sleeps;

import cz.cvut.fel.pc2e.garminworker.repository.DeviceDao;
import cz.cvut.fel.pc2e.garminworker.repository.SleepSummaryDao;
import cz.cvut.fel.pc2e.garminworker.model.dto.sleeps.SleepSummaryDto;
import cz.cvut.fel.pc2e.garminworker.model.dto.sleeps.SleepsPushNotificationDto;
import cz.cvut.fel.pc2e.garminworker.model.entities.sleeps.SleepSummary;
import cz.cvut.fel.pc2e.garminworker.services.TimeComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SleepsService {
    private final DeviceDao deviceRepository;
    private final SleepSummaryDao sleepSummaryDao;
    private final SleepsDtoToEntityConverter sleepsDtoToEntityConverter;
    private final TimeComponent timeComponent;

    @Autowired
    public SleepsService(DeviceDao deviceRepository,
            SleepSummaryDao sleepSummaryDao,
            SleepsDtoToEntityConverter sleepsDtoToEntityConverter,
            TimeComponent timeComponent) {
        this.deviceRepository = deviceRepository;
        this.sleepSummaryDao = sleepSummaryDao;
        this.sleepsDtoToEntityConverter = sleepsDtoToEntityConverter;
        this.timeComponent = timeComponent;
    }

    public List<SleepSummary> getSleepsFromLastNDays(int numOfDays) {
        return sleepSummaryDao
                .findAllSorted(
                        timeComponent.getEpochBoundary(numOfDays)
                );
    }
    public SleepSummary getSleepSummaryById(Integer id) {
        Optional<SleepSummary> s = sleepSummaryDao.findById(id);
        return s.orElse(null);
    }

    /**
     * Method receives SleepsPushNotification containing a list of SleepSummaryDtos.
     * For each SleepSummaryDto process it.
     */
    public void processSleepsPushNotificationDto(SleepsPushNotificationDto sleepsPushNotification) {
        if (sleepsPushNotification != null && sleepsPushNotification.getSleeps() != null) {
            List<SleepSummaryDto> sleeps = sleepsPushNotification.getSleeps();
            for (SleepSummaryDto sleepSummary : sleeps) {
                this.processSleepSummaryDTO(sleepSummary);
            }
        }
    }

    @Transactional
    protected void processSleepSummaryDTO(SleepSummaryDto sleepSummaryDto) {
        log.debug("Processing sleepSummaryDTO: {} ", sleepSummaryDto);
        // first check if sleep summary with summaryId already exists
        Optional<SleepSummary> existingSummaryOpt = sleepSummaryDao.findBySummaryId(sleepSummaryDto.getSummaryId());
        // process SleepSummaryDto into SleepSummary business object
        SleepSummary sleepSummary = sleepsDtoToEntityConverter.convertDtoToHibernateEntity(sleepSummaryDto);

        if (sleepSummary.getUserAccessToken() == null) {
            log.warn("Skipping sleep summary, because userAccessToken is missing. sleep: {}", sleepSummary);
            return;
        }
        if (sleepSummary.getStartTimeInSeconds() == null || sleepSummary.getDurationInSeconds() == null) {
            log.warn("Skipping sleep summary, because startTime or duration is missing. sleep: {}", sleepSummary);
            return;
        }

		Optional<Integer> deviceId = deviceRepository.findDeviceIdByUserAccessToken(sleepSummary.getDevice().getUserAccessToken());

		if (deviceId.isEmpty()) {
			log.warn("device id not found");
			return;
		}
		sleepSummary.getDevice().setId(deviceId.get());
		if (existingSummaryOpt.isPresent()) {
			SleepSummary existingSummary = existingSummaryOpt.get();
			log.debug("Existing sleep summary:     {}", existingSummary);
			log.debug("An updated summary came. Going to overwrite it.");

			sleepSummary.setId(existingSummaryOpt.get().getId());
		}

		this.sleepSummaryDao.save(sleepSummary);
    }

}
