package cz.cvut.fel.pc2e.garminworker.model.dto.sleeps;

import com.fasterxml.jackson.annotation.JsonProperty;
import cz.cvut.fel.pc2e.garminworker.model.domain.ValidationTypeEnum;
import cz.cvut.fel.pc2e.garminworker.model.entities.sleeps.SleepLevelTimeRange;

import java.util.List;
import java.util.Map;

public class SleepSummaryDto extends SleepSummaryLiteDto{
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

    public SleepSummaryDto() {
        super();
    }

    public Map<String, List<SleepLevelTimeRange>> getSleepLevelsMap() {
        return sleepLevelsMap;
    }

    public void setSleepLevelsMap(Map<String, List<SleepLevelTimeRange>> sleepLevelsMap) {
        this.sleepLevelsMap = sleepLevelsMap;
    }

    public ValidationTypeEnum getValidation() {
        return validation;
    }

    public void setValidation(ValidationTypeEnum validation) {
        this.validation = validation;
    }

    public Map<Integer, Integer> getTimeOffsetSleepRespiration() {
        return timeOffsetSleepRespiration;
    }

    public void setTimeOffsetSleepRespiration(Map<Integer, Integer> timeOffsetSleepRespiration) {
        this.timeOffsetSleepRespiration = timeOffsetSleepRespiration;
    }

    public Map<Integer, Integer> getTimeOffsetSleepSpo2() {
        return timeOffsetSleepSpo2;
    }

    public void setTimeOffsetSleepSpo2(Map<Integer, Integer> timeOffsetSleepSpo2) {
        this.timeOffsetSleepSpo2 = timeOffsetSleepSpo2;
    }

    public Map<String, Object> getOverallSleepScore() {
        return overallSleepScore;
    }

    public void setOverallSleepScore(Map<String, Object> overallSleepScore) {
        this.overallSleepScore = overallSleepScore;
    }

    public Map<String, Object> getSleepScores() {
        return sleepScores;
    }

    public void setSleepScores(Map<String, Object> sleepScores) {
        this.sleepScores = sleepScores;
    }
}


