package cz.cvut.fel.pc2e.garminworker.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import cz.cvut.fel.pc2e.garminworker.entities.ValidationTypeEnum;
import cz.cvut.fel.pc2e.garminworker.entities.sleeps.SleepLevelTimeRange;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
public class SleepSummaryDto {

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

    @JsonProperty("sleepLevelsMap")
    private Map<String, List<SleepLevelTimeRange>> sleepLevelsMap;

    @JsonProperty("validation")
    private ValidationTypeEnum validation;

    @JsonProperty("timeOffsetSleepRespiration")
    private Map<Integer, Integer> timeOffsetSleepRespiration;

    @JsonProperty("timeOffsetSleepSpo2")
    private Map<Integer, Integer> timeOffsetSleepSpo2;

    @JsonProperty("overallSleepScore")
    private Map<String, Object> overallSleepScore;

    @JsonProperty("sleepScores")
    private Map<String, Object> sleepScores;
}


