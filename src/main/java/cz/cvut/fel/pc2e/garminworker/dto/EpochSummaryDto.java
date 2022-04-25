package cz.cvut.fel.pc2e.garminworker.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
public class EpochSummaryDto {

    @JsonProperty("userId")
    private String userId;

    @JsonProperty("userAccessToken")
    private String userAccessToken;

    @JsonProperty("summaryId")
    private String summaryId;

    @JsonProperty("activityType")
    private ActivityTypeEnum activityType;

    @JsonProperty("activeKilocalories")
    private int activeKilocalories;

    @JsonProperty("steps")
    private int steps;

    @JsonProperty("distanceInMeters")
    private double distanceInMeters;

    @JsonProperty("durationInSeconds")
    private int durationInSeconds;

    @JsonProperty("activeTimeInSeconds")
    private int activeTimeInSeconds;

    @JsonProperty("met")
    private double met;

    @JsonProperty("intensity")
    private ActivityIntensityEnum intensity;

    @JsonProperty("startTimeInSeconds")
    private long startTimeInSeconds;

    @JsonProperty("startTimeOffsetInSeconds")
    private long startTimeOffsetInSeconds;

    @JsonProperty("meanMotionIntensity")
    private double meanMotionIntensity;

    @JsonProperty("maxMotionIntensity")
    private double maxMotionIntensity;

    @JsonProperty("timeOffsetBodyBatterySamples")
    private Map<String, Integer> timeOffsetBodyBatterySamples;
}


