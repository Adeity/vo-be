package cz.cvut.fel.pc2e.garminworker.kafka.messages;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class GenericMessage {
    private String deviceId;
    private Timestamp messageTimestamp;

    public GenericMessage() {
    }

    public GenericMessage(String deviceId, Timestamp messageTimestamp) {
        this.deviceId = deviceId;
        this.messageTimestamp = messageTimestamp;
    }
}
