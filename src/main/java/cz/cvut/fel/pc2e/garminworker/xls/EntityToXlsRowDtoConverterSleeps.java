package cz.cvut.fel.pc2e.garminworker.xls;

import cz.cvut.fel.pc2e.garminworker.xls.SleepsXlsRowDto;
import cz.cvut.fel.pc2e.garminworker.model.entities.sleeps.SleepSummary;
import cz.cvut.fel.pc2e.garminworker.services.DeviceService;
import cz.cvut.fel.pc2e.garminworker.services.TimeComponent;
import cz.cvut.fel.pc2e.garminworker.xls.EntityToXlsRowDtoConverter;
import cz.cvut.fel.pc2e.garminworker.xls.XlsRowDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class EntityToXlsRowDtoConverterSleeps implements EntityToXlsRowDtoConverter<SleepSummary> {
	private final TimeComponent timeComponent = new TimeComponent();
	private final DeviceService deviceService;

	@Autowired
	public EntityToXlsRowDtoConverterSleeps(DeviceService deviceService) {
		this.deviceService = deviceService;
	}

	@Override public List<XlsRowDto> convertEntitiesToXlsDto(List<SleepSummary> entityList) {
		List<XlsRowDto> res = new ArrayList<>();

		String researchNumber = null;
		boolean sameUserAccessToken = false;
		int index = 0;

		for (SleepSummary s : entityList) {
			SleepsXlsRowDto sleepXlsRow = new SleepsXlsRowDto();

			if (!sameUserAccessToken) {
				researchNumber = deviceService
						.getAllResearchIdsByOauthToken(
								s.getUserAccessToken()
						);
			}
			// get research number and see if it exists
			if (researchNumber == null) {
				log.debug("Didnt find any researh number for {}, skipping SleepSummary", s.getUserAccessToken());
				sameUserAccessToken = isSameUserAccessToken(s, entityList, index);
				index++;
				continue;
			}
			sleepXlsRow.setResearchNumberValue(
					researchNumber
			);
			sleepXlsRow.setSummaryIdValue(
					s.getSummaryId()
			);
			sleepXlsRow.setResearchNumberValue(
					researchNumber
			);
			sleepXlsRow.setCalendarDateValue(
					timeComponent.reformatCalendarDate(s.getCalendarDate())
			);
			sleepXlsRow.setStartTimeValue(
					timeComponent.unixTimeToHours(
							s.getStartTimeInSeconds()
					)
			);

			Long startTime = s.getStartTimeInSeconds();
			Integer duration = s.getDurationInSeconds();
			Integer awakeTime = s.getAwakeDurationInSeconds();
			Long endTime = startTime + duration + awakeTime;
			sleepXlsRow.setEndTimeValue(
					timeComponent.unixTimeToHours(
							endTime
					)
			);

			sleepXlsRow.setDurationValue(
					timeComponent.secondsToHourMinuteFormat(
							s.getDurationInSeconds()
					)
			);
			sleepXlsRow.setDeepSleepDurationValue(
					timeComponent.secondsToHourMinuteFormat(
							s.getDeepSleepDurationInSeconds()
					)
			);
			sleepXlsRow.setLightSleepDurationValue(
					timeComponent.secondsToHourMinuteFormat(
							s.getLightSleepDurationInSeconds()
					)
			);
			sleepXlsRow.setRemSleepDurationValue(
					timeComponent.secondsToHourMinuteFormat(
							s.getRemSleepInSeconds()
					)
			);
			sleepXlsRow.setAwakeDurationValue(
					timeComponent.secondsToHourMinuteFormat(
							s.getAwakeDurationInSeconds()
					)
			);
			sleepXlsRow.setUnmeasurableSleepDurationValue(
					timeComponent.secondsToHourMinuteFormat(
							s.getUnmeasurableSleepInSeconds()
					)
			);
			sleepXlsRow.setValidationValue(s.getValidation());

			res.add(sleepXlsRow);

			sameUserAccessToken = isSameUserAccessToken(s, entityList, index);
			index++;
		}

		return res;
	}

	private boolean isSameUserAccessToken(SleepSummary s, List<SleepSummary> sleepSummaries, int index) {
		if (index >= sleepSummaries.size() - 1) {
			return true;
		}

		String currentUserAccessToken = s.getUserAccessToken();
		String nextUserAccessToken = sleepSummaries.get(index + 1).getUserAccessToken();
		return currentUserAccessToken.equals(nextUserAccessToken);
	}
}
