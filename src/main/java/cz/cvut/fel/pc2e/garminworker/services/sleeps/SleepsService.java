package cz.cvut.fel.pc2e.garminworker.services.sleeps;

import cz.cvut.fel.pc2e.garminworker.model.dto.sleeps.SleepSummaryFilterDto;
import cz.cvut.fel.pc2e.garminworker.repository.SleepSummaryDao;
import cz.cvut.fel.pc2e.garminworker.model.dto.sleeps.SleepsPushNotificationDto;
import cz.cvut.fel.pc2e.garminworker.model.entities.sleeps.SleepSummary;
import cz.cvut.fel.pc2e.garminworker.services.TimeComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class SleepsService {
    private final SleepSummaryDao sleepSummaryDao;
	private final SleepsSummaryPushProcesserService sleepsSummaryPushProcesserService;
    private final TimeComponent timeComponent;

    @Autowired
    public SleepsService(
            SleepSummaryDao sleepSummaryDao,
			SleepsSummaryPushProcesserService sleepsSummaryPushProcesserService, TimeComponent timeComponent
	) {
        this.sleepSummaryDao = sleepSummaryDao;
		this.sleepsSummaryPushProcesserService = sleepsSummaryPushProcesserService;
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

	@Transactional
    public void processSleepsPushNotificationDto(SleepsPushNotificationDto sleepsPushNotification) {
		sleepsSummaryPushProcesserService.processSleepsPushNotification(sleepsPushNotification);
    }

	@Transactional(readOnly = true)
	public List<SleepSummary> read(SleepSummaryFilterDto filter) {
		return sleepSummaryDao.find(filter);
	}
}
