package cz.cvut.fel.pc2e.garminworker.model.dto.sleeps;

import cz.cvut.fel.pc2e.garminworker.model.domain.ValidationTypeEnum;

public class SleepsXlsRowDto {
    private String researchNumber;
    private String summaryId;
    private String calendarDate;
    private String duration;
    private String startTime;
    private String endTime;
    private String unmeasurableSleepDuration;
    private String deepSleepDuration;
    private String lightSleepDuration;
    private String remSleepDuration ;
    private String awakeDuration ;

    private ValidationTypeEnum validation;

    public void setValidation(ValidationTypeEnum validation) {
        this.validation = validation;
    }

    public void setResearchNumber(String researchNumber) {
        this.researchNumber = researchNumber;
    }

    public void setCalendarDate(String calendarDate) {
        this.calendarDate = calendarDate;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public void setUnmeasurableSleepDuration(String unmeasurableSleepDuration) {
        this.unmeasurableSleepDuration = unmeasurableSleepDuration;
    }

    public void setDeepSleepDuration(String deepSleepDuration) {
        this.deepSleepDuration = deepSleepDuration;
    }

    public void setLightSleepDuration(String lightSleepDuration) {
        this.lightSleepDuration = lightSleepDuration;
    }

    public void setRemSleepDuration(String remSleepDuration) {
        this.remSleepDuration = remSleepDuration;
    }

    public void setAwakeDuration(String awakeDuration) {
        this.awakeDuration = awakeDuration;
    }

    public void setSummaryId(String summaryId) {
        this.summaryId = summaryId;
    }

    public String getResearchNumber() {
        return researchNumber;
    }

    public String getSummaryId() {
        return summaryId;
    }

    public String getCalendarDate() {
        return calendarDate;
    }

    public String getDuration() {
        return duration;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public String getUnmeasurableSleepDuration() {
        return unmeasurableSleepDuration;
    }

    public String getDeepSleepDuration() {
        return deepSleepDuration;
    }

    public String getLightSleepDuration() {
        return lightSleepDuration;
    }

    public String getRemSleepDuration() {
        return remSleepDuration;
    }

    public String getAwakeDuration() {
        return awakeDuration;
    }

    public ValidationTypeEnum getValidation() {
        return validation;
    }
}
