package cz.cvut.fel.pc2e.garminworker.cloud.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
public class DailySummaryDto {

    @JsonProperty("activeKilocalories")
    private Integer activeKilocalories;

    @JsonProperty("activeTimeInSeconds")
    private Integer activeTimeInSeconds;

    @JsonProperty("activityStressDurationInSeconds")
    private Integer activityStressDurationInSeconds;

    @JsonProperty("activityType")
    private String activityType;

    @JsonProperty("averageHeartRateInBeatsPerMinute")
    private Integer averageHeartRateInBeatsPerMinute;

    @JsonProperty("averageStressLevel")
    private Integer averageStressLevel;

    @JsonProperty("bmrKilocalories")
    private Integer bmrKilocalories;

    @JsonProperty("calendarDate")
    private String calendarDate;

    @JsonProperty("distanceInMeters")
    private Double distanceInMeters;

    @JsonProperty("durationInSeconds")
    private Integer durationInSeconds;

    @JsonProperty("floorsClimbed")
    private Integer floorsClimbed;

    @JsonProperty("floorsClimbedGoal")
    private Integer floorsClimbedGoal;

    @JsonProperty("highStressDurationInSeconds")
    private Integer highStressDurationInSeconds;

    @JsonProperty("intensityDurationGoalInSeconds")
    private Integer intensityDurationGoalInSeconds;

    @JsonProperty("lowStressDurationInSeconds")
    private Integer lowStressDurationInSeconds;

    @JsonProperty("maxHeartRateInBeatsPerMinute")
    private Integer maxHeartRateInBeatsPerMinute;

    @JsonProperty("maxStressLevel")
    private Integer maxStressLevel;

    @JsonProperty("mediumStressDurationInSeconds")
    private Integer mediumStressDurationInSeconds;

    @JsonProperty("minHeartRateInBeatsPerMinute")
    private Integer minHeartRateInBeatsPerMinute;

    @JsonProperty("moderateIntensityDurationInSeconds")
    private Integer moderateIntensityDurationInSeconds;

    @JsonProperty("restStressDurationInSeconds")
    private Integer restStressDurationInSeconds;

    @JsonProperty("restingHeartRateInBeatsPerMinute")
    private Integer restingHeartRateInBeatsPerMinute;

    @JsonProperty("startTimeInSeconds")
    private Long startTimeInSeconds;

    @JsonProperty("startTimeOffsetInSeconds")
    private Integer startTimeOffsetInSeconds;

    @JsonProperty("steps")
    private Integer steps;

    @JsonProperty("stepsGoal")
    private Integer stepsGoal;

    @JsonProperty("stressDurationInSeconds")
    private Integer stressDurationInSeconds;

    @JsonProperty("stressQualifier")
    private String stressQualifier;

    @JsonProperty("summaryId")
    private String summaryId;

    @JsonProperty("timeOffsetHeartRateSamples")
    private Map<Integer, Integer> timeOffsetHeartRateSamples;

    @JsonProperty("userAccessToken")
    private String userAccessToken;

    @JsonProperty("userId")
    private String userId;

    @JsonProperty("vigorousIntensityDurationInSeconds")
    private Integer vigorousIntensityDurationInSeconds;

    @JsonProperty("netKilocaloriesGoal")
    private Integer netKilocaloriesGoal;
}
