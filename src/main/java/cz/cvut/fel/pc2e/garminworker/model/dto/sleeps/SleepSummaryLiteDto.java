package cz.cvut.fel.pc2e.garminworker.model.dto.sleeps;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SleepSummaryLiteDto {
    @JsonProperty("userId")
    private String userId;

    @JsonProperty("userAccessToken")
    private String userAccessToken;

    @JsonProperty("summaryId")
    private String summaryId;

    @JsonProperty("calendarDate")
    private String calendarDate;

    @JsonProperty("startTimeInSeconds")
    private Long startTimeInSeconds;

    @JsonProperty("startTimeOffsetInSeconds")
    private Long startTimeOffsetInSeconds;

    @JsonProperty("durationInSeconds")
    private Integer durationInSeconds;

    @JsonProperty("unmeasurableSleepInSeconds")
    private Integer unmeasurableSleepInSeconds;

    @JsonProperty("deepSleepDurationInSeconds")
    private Integer deepSleepDurationInSeconds;

    @JsonProperty("lightSleepDurationInSeconds")
    private Integer lightSleepDurationInSeconds;

    @JsonProperty("remSleepInSeconds")
    private Integer remSleepInSeconds;

    @JsonProperty("awakeDurationInSeconds")
    private Integer awakeDurationInSeconds;

    public SleepSummaryLiteDto() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserAccessToken() {
        return userAccessToken;
    }

    public void setUserAccessToken(String userAccessToken) {
        this.userAccessToken = userAccessToken;
    }

    public String getSummaryId() {
        return summaryId;
    }

    public void setSummaryId(String summaryId) {
        this.summaryId = summaryId;
    }

    public String getCalendarDate() {
        return calendarDate;
    }

    public void setCalendarDate(String calendarDate) {
        this.calendarDate = calendarDate;
    }

    public Long getStartTimeInSeconds() {
        return startTimeInSeconds;
    }

    public void setStartTimeInSeconds(Long startTimeInSeconds) {
        this.startTimeInSeconds = startTimeInSeconds;
    }

    public Long getStartTimeOffsetInSeconds() {
        return startTimeOffsetInSeconds;
    }

    public void setStartTimeOffsetInSeconds(Long startTimeOffsetInSeconds) {
        this.startTimeOffsetInSeconds = startTimeOffsetInSeconds;
    }

    public Integer getDurationInSeconds() {
        return durationInSeconds;
    }

    public void setDurationInSeconds(Integer durationInSeconds) {
        this.durationInSeconds = durationInSeconds;
    }

    public Integer getUnmeasurableSleepInSeconds() {
        return unmeasurableSleepInSeconds;
    }

    public void setUnmeasurableSleepInSeconds(Integer unmeasurableSleepInSeconds) {
        this.unmeasurableSleepInSeconds = unmeasurableSleepInSeconds;
    }

    public Integer getDeepSleepDurationInSeconds() {
        return deepSleepDurationInSeconds;
    }

    public void setDeepSleepDurationInSeconds(Integer deepSleepDurationInSeconds) {
        this.deepSleepDurationInSeconds = deepSleepDurationInSeconds;
    }

    public Integer getLightSleepDurationInSeconds() {
        return lightSleepDurationInSeconds;
    }

    public void setLightSleepDurationInSeconds(Integer lightSleepDurationInSeconds) {
        this.lightSleepDurationInSeconds = lightSleepDurationInSeconds;
    }

    public Integer getRemSleepInSeconds() {
        return remSleepInSeconds;
    }

    public void setRemSleepInSeconds(Integer remSleepInSeconds) {
        this.remSleepInSeconds = remSleepInSeconds;
    }

    public Integer getAwakeDurationInSeconds() {
        return awakeDurationInSeconds;
    }

    public void setAwakeDurationInSeconds(Integer awakeDurationInSeconds) {
        this.awakeDurationInSeconds = awakeDurationInSeconds;
    }
}
