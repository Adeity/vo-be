package cz.cvut.fel.pc2e.garminworker.model.entities.sleeps;

import cz.cvut.fel.pc2e.garminworker.model.entities.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(
        name = "time_offset_sleep_spo2"
)
public class TimeOffsetSleepSpo2 extends AbstractEntity {
    @NotNull
    @Column(
            name = "key"
    )
    private Integer key;
    @NotNull
    @Column(
            name = "value"
    )
    private Integer value;

    public TimeOffsetSleepSpo2() {
    }

    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
