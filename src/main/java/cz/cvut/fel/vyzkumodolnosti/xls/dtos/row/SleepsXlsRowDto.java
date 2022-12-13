package cz.cvut.fel.vyzkumodolnosti.xls.dtos.row;

import cz.cvut.fel.vyzkumodolnosti.xls.dtos.XlsDto;

import java.util.ArrayList;
import java.util.List;

public class SleepsXlsRowDto implements XlsRowDto {
	private final XlsDto summaryId
			= new XlsDto("ID", "ID");
	private final XlsDto researchNumber
			= new XlsDto("Výzkumné číslo", "Research number");
    private final XlsDto calendarDate
			= new XlsDto("Datum", "Date");
	private final XlsDto duration
			= new XlsDto("Délka spánku", "Duration");
    private final XlsDto startTime
			= new XlsDto("Začátek spánku", "Start time");
	private final XlsDto endTime
			= new XlsDto("Konec spánku", "End time");
    private final XlsDto unmeasurableSleepDuration
			= new XlsDto("Neměřitelná doba spánku", "Unmeasurable sleep");
    private final XlsDto deepSleepDuration
			= new XlsDto("Hluboký spánek", "Deep sleep");
    private final XlsDto lightSleepDuration
			= new XlsDto("Lehký spánek", "Light sleep");
    private final XlsDto remSleepDuration
			= new XlsDto("REM", "REM");
    private final XlsDto awakeDuration
			= new XlsDto("Vzhůru", "Awake duration");
    private final XlsDto validation
			= new XlsDto("Validace", "Validation");

	private List<XlsDto> parameters;

	@Override
	public List<XlsDto> getParameters() {
		if (this.parameters != null) {
			return this.parameters;
		}
		parameters = new ArrayList<>();
		parameters.add(this.summaryId);
		parameters.add(this.researchNumber);
		parameters.add(this.calendarDate);
		parameters.add(this.duration);
		parameters.add(this.startTime);
		parameters.add(this.endTime);
		parameters.add(this.unmeasurableSleepDuration);
		parameters.add(this.deepSleepDuration);
		parameters.add(this.lightSleepDuration);
		parameters.add(this.remSleepDuration);
		parameters.add(this.awakeDuration);
		parameters.add(this.validation);
		return this.parameters;
	}

	@Override public String getResearchNumberValue() {
		return this.researchNumber.getValue().toString();
	}

	public void setSummaryIdValue(Object summaryId) {
		this.summaryId.setValue(summaryId);
	}

	public void setResearchNumberValue(Object researchNumber) {
		this.researchNumber.setValue(researchNumber);
	}

	public void setCalendarDateValue(Object calendarDate) {
		this.calendarDate.setValue(calendarDate);
	}

	public void setDurationValue(Object duration) {
		this.duration.setValue(duration);
	}

	public void setStartTimeValue(Object startTime) {
		this.startTime.setValue(startTime);
	}

	public void setEndTimeValue(Object endTime) {
		this.endTime.setValue(endTime);
	}

	public void setUnmeasurableSleepDurationValue(Object unmeasurableSleepDuration) {
		this.unmeasurableSleepDuration.setValue(unmeasurableSleepDuration);
	}

	public void setDeepSleepDurationValue(Object deepSleepDuration) {
		this.deepSleepDuration.setValue(deepSleepDuration);
	}

	public void setLightSleepDurationValue(Object lightSleepDuration) {
		this.lightSleepDuration.setValue(lightSleepDuration);
	}

	public void setRemSleepDurationValue(Object remSleepDuration) {
		this.remSleepDuration.setValue(remSleepDuration);
	}

	public void setAwakeDurationValue(Object awakeDuration) {
		this.awakeDuration.setValue(awakeDuration);
	}

	public void setValidationValue(Object validation) {
		this.validation.setValue(validation);
	}
}
