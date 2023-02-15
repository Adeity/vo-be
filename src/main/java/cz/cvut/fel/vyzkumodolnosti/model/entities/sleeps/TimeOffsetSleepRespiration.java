package cz.cvut.fel.vyzkumodolnosti.model.entities.sleeps;

import cz.cvut.fel.vyzkumodolnosti.model.entities.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(
        name = "time_offset_sleep_respiration"
)
public class TimeOffsetSleepRespiration extends AbstractEntity {
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

    public TimeOffsetSleepRespiration() {
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
