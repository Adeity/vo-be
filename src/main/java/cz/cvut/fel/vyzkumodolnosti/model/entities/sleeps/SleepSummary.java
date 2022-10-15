package cz.cvut.fel.vyzkumodolnosti.model.entities.sleeps;

import cz.cvut.fel.vyzkumodolnosti.model.entities.AbstractEntity;
import cz.cvut.fel.vyzkumodolnosti.model.domain.ValidationTypeEnum;
import cz.cvut.fel.vyzkumodolnosti.model.entities.DeviceEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "sleeps")
public class SleepSummary extends AbstractEntity {

	@NotNull
	@Column(name = "user_access_token")
	private String userAccessToken;

    @NotNull
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(
            name = "device_id",
			referencedColumnName = "id"
    )
    private DeviceEntity device;

    @NotNull
    @Column(
            name = "summary_id"
    )
    private String summaryId;

    @NotNull
    @Column(
            name = "calendar_date"
    )
    private String calendarDate;

    @NotNull
    @Column(
            name = "duration_in_seconds"
    )
    private Integer durationInSeconds;

    @NotNull
    @Column(
            name = "start_time_in_seconds"
    )
    private Long startTimeInSeconds;

    @NotNull
    @Column(
            name = "start_time_offset_in_seconds"
    )
    private Long startTimeOffsetInSeconds;

    @NotNull
    @Column(
            name = "unmeasurable_sleep_in_seconds"
    )
    private Integer unmeasurableSleepInSeconds;

    @NotNull
    @Column(
            name = "deep_sleep_duration_in_seconds"
    )
    private Integer deepSleepDurationInSeconds;

    @NotNull
    @Column(
            name = "light_sleep_duration_in_seconds"
    )
    private Integer lightSleepDurationInSeconds;

    @NotNull
    @Column(
            name = "rem_sleep_in_seconds"
    )
    private Integer remSleepInSeconds;

    @NotNull
    @Column(
            name = "awake_duration_in_seconds"
    )
    private Integer awakeDurationInSeconds;

    @OneToOne(cascade = CascadeType.ALL)
    private SleepLevelsMap sleepLevelsMap;

    @NotNull
    @Column(
            name = "validation"
    )
    private ValidationTypeEnum validation;

    @OneToMany(cascade = CascadeType.ALL)
    private List<TimeOffsetSleepRespiration> timeOffsetSleepRespiration;

    @OneToMany(cascade = CascadeType.ALL)
    private List<TimeOffsetSleepSpo2> timeOffsetSleepSpo2;

    @OneToOne(cascade = CascadeType.ALL)
    private OverallSleepScore overallSleepScore;

    @OneToMany(cascade = CascadeType.ALL)
    private List<SleepScore> sleepScores;

	public String getUserAccessToken() {
		return userAccessToken;
	}

	public void setUserAccessToken(String userAccessToken) {
		this.userAccessToken = userAccessToken;
	}

    public SleepSummary() {
    }

	public DeviceEntity getDevice() {
		return device;
	}

	public void setDevice(DeviceEntity userAccessToken) {
		this.device = userAccessToken;
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

    public Integer getDurationInSeconds() {
        return durationInSeconds;
    }

    public void setDurationInSeconds(Integer durationInSeconds) {
        this.durationInSeconds = durationInSeconds;
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

    public SleepLevelsMap getSleepLevelsMap() {
        return sleepLevelsMap;
    }

    public void setSleepLevelsMap(SleepLevelsMap sleepLevelsMap) {
        this.sleepLevelsMap = sleepLevelsMap;
    }

    public ValidationTypeEnum getValidation() {
        return validation;
    }

    public void setValidation(ValidationTypeEnum validation) {
        this.validation = validation;
    }

    public List<TimeOffsetSleepRespiration> getTimeOffsetSleepRespiration() {
        return timeOffsetSleepRespiration;
    }

    public void setTimeOffsetSleepRespiration(List<TimeOffsetSleepRespiration> timeOffsetSleepRespiration) {
        this.timeOffsetSleepRespiration = timeOffsetSleepRespiration;
    }

    public List<TimeOffsetSleepSpo2> getTimeOffsetSleepSpo2() {
        return timeOffsetSleepSpo2;
    }

    public void setTimeOffsetSleepSpo2(List<TimeOffsetSleepSpo2> timeOffsetSleepSpo2) {
        this.timeOffsetSleepSpo2 = timeOffsetSleepSpo2;
    }

    public OverallSleepScore getOverallSleepScore() {
        return overallSleepScore;
    }

    public void setOverallSleepScore(OverallSleepScore overallSleepScore) {
        this.overallSleepScore = overallSleepScore;
    }

    public List<SleepScore> getSleepScores() {
        return sleepScores;
    }

    public void setSleepScores(List<SleepScore> sleepScores) {
        this.sleepScores = sleepScores;
    }

}
