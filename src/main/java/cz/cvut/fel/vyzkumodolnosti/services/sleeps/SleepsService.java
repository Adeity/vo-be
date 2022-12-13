package cz.cvut.fel.vyzkumodolnosti.services.sleeps;

import cz.cvut.fel.vyzkumodolnosti.model.dto.sleeps.SleepSummaryFilterDto;
import cz.cvut.fel.vyzkumodolnosti.repository.sleep.SleepSummaryRepository;
import cz.cvut.fel.vyzkumodolnosti.model.dto.sleeps.SleepsPushNotificationDto;
import cz.cvut.fel.vyzkumodolnosti.model.entities.sleeps.SleepSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class SleepsService {
    private final SleepSummaryRepository sleepSummaryRepository;
	private final SleepsSummaryPushProcesserService sleepsSummaryPushProcesserService;

    @Autowired
    public SleepsService(
            SleepSummaryRepository sleepSummaryRepository,
			SleepsSummaryPushProcesserService sleepsSummaryPushProcesserService) {
        this.sleepSummaryRepository = sleepSummaryRepository;
		this.sleepsSummaryPushProcesserService = sleepsSummaryPushProcesserService;
    }

	@Transactional
    public void processSleepsPushNotificationDto(SleepsPushNotificationDto sleepsPushNotification) {
		sleepsSummaryPushProcesserService.processSleepsPushNotification(sleepsPushNotification);
    }

	@Transactional(readOnly = true)
	public List<SleepSummary> read(SleepSummaryFilterDto filter) {
		return sleepSummaryRepository.find(filter);
	}
}
