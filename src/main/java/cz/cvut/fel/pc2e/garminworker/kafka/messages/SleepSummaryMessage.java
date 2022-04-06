package cz.cvut.fel.pc2e.garminworker.kafka.messages;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.sql.Timestamp;

@EqualsAndHashCode(callSuper = true)
@Data
public class SleepSummaryMessage extends GenericMessage {

    private String calendarDate;
    private Long startTimeInSeconds;
    private Long endTimeInSeconds;
    private Integer durationInSeconds;
    private Integer unmeasurableSleepDurationInSeconds;
    private Integer deepSleepDurationInSeconds;
    private Integer lightSleepDurationInSeconds;
    private Integer remSleepInSeconds;
    private Integer awakeDurationInSeconds;

    public SleepSummaryMessage() {
    }

    public SleepSummaryMessage(String deviceId, Timestamp messageTimestamp, String calendarDate, Long startTimeInSeconds, Long endTimeInSeconds, Integer durationInSeconds, Integer unmeasurableSleepDurationInSeconds, Integer deepSleepDurationInSeconds, Integer lightSleepDurationInSeconds, Integer remSleepInSeconds, Integer awakeDurationInSeconds) {
        super(deviceId, messageTimestamp);
        this.calendarDate = calendarDate;
        this.startTimeInSeconds = startTimeInSeconds;
        this.endTimeInSeconds = endTimeInSeconds;
        this.durationInSeconds = durationInSeconds;
        this.unmeasurableSleepDurationInSeconds = unmeasurableSleepDurationInSeconds;
        this.deepSleepDurationInSeconds = deepSleepDurationInSeconds;
        this.lightSleepDurationInSeconds = lightSleepDurationInSeconds;
        this.remSleepInSeconds = remSleepInSeconds;
        this.awakeDurationInSeconds = awakeDurationInSeconds;
    }
}
