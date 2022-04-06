package cz.cvut.fel.pc2e.garminworker.entities;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "period_offset")
public class PeriodOffsetEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private Integer deviceId;
    private Timestamp epochStartTime;
    private int offsetInPeriod = 0;

    public Integer getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Integer deviceId) {
        this.deviceId = deviceId;
    }

    public Timestamp getEpochStartTime() {
        return epochStartTime;
    }

    public void setEpochStartTime(Timestamp epochStartTime) {
        this.epochStartTime = epochStartTime;
    }

    public int getOffsetInPeriod() {
        return offsetInPeriod;
    }

    public void setOffsetInPeriod(int offset) {
        this.offsetInPeriod = offset;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
