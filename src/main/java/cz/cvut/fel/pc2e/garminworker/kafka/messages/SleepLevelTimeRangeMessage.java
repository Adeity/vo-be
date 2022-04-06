package cz.cvut.fel.pc2e.garminworker.kafka.messages;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.sql.Timestamp;

@EqualsAndHashCode(callSuper = true)
@Data
public class SleepLevelTimeRangeMessage extends GenericMessage {

    private String calendarDate;
    private Long startTimeInSeconds;
    private Long endTimeInSeconds;
    private SleepLevelTypeEnum sleepLevel;

    public SleepLevelTimeRangeMessage() {
    }

    public SleepLevelTimeRangeMessage(String deviceId, Timestamp messageTimestamp, String calendarDate, Long startTimeInSeconds, Long endTimeInSeconds, SleepLevelTypeEnum sleepLevel) {
        super(deviceId, messageTimestamp);
        this.calendarDate = calendarDate;
        this.startTimeInSeconds = startTimeInSeconds;
        this.endTimeInSeconds = endTimeInSeconds;
        this.sleepLevel = sleepLevel;
    }
}
