package cz.cvut.fel.pc2e.garminworker.kafka.messages;


import cz.cvut.fel.pc2e.garminworker.cloud.api.dto.ActivityTypeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.sql.Timestamp;

@EqualsAndHashCode(callSuper = true)
@Data
public class ActivityMessage extends GenericMessage {

    private long datetimeInSeconds;
    private ActivityTypeEnum activityType;
    private int steps;
    private int intensity;
    private double met;

    public ActivityMessage() {
    }

    public ActivityMessage(String deviceId, long datetimeInSeconds, ActivityTypeEnum activityType, int steps, int intensity, double met, Timestamp messageTimestamp) {
        super(deviceId, messageTimestamp);
        this.datetimeInSeconds = datetimeInSeconds;
        this.activityType = activityType;
        this.steps = steps;
        this.intensity = intensity;
        this.met = met;
    }
}
