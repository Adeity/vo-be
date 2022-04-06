package cz.cvut.fel.pc2e.garminworker.kafka.messages;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.sql.Timestamp;

@EqualsAndHashCode(callSuper = true)
@Data
public class HeartRateMessage extends GenericMessage {

    private long datetimeInSeconds;
    private Integer heartRate;

    public HeartRateMessage() {
    }

    public HeartRateMessage(String deviceId, long datetimeInSeconds, Integer heartRate, Timestamp messageTimestamp) {
        super(deviceId, messageTimestamp);
        this.datetimeInSeconds = datetimeInSeconds;
        this.heartRate = heartRate;
    }

}
