package cz.cvut.fel.vyzkumodolnosti.model.entities.sleeps;

import cz.cvut.fel.vyzkumodolnosti.model.entities.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(
        name = "sleep_level_time_range"
)
public class SleepLevelTimeRange extends AbstractEntity {
    @NotNull
    @Column(
            name = "start_time_in_seconds"
    )
    private Long startTimeInSeconds;

    @NotNull
    @Column(
            name = "end_time_in_seconds"
    )
    private Long endTimeInSeconds;

    public SleepLevelTimeRange() {
    }

    public SleepLevelTimeRange(Long startTimeInSeconds, Long endTimeInSeconds) {
        this.startTimeInSeconds = startTimeInSeconds;
        this.endTimeInSeconds = endTimeInSeconds;
    }

    public Long getStartTimeInSeconds() {
        return startTimeInSeconds;
    }

    public void setStartTimeInSeconds(Long startTimeInSeconds) {
        this.startTimeInSeconds = startTimeInSeconds;
    }

    public Long getEndTimeInSeconds() {
        return endTimeInSeconds;
    }

    public void setEndTimeInSeconds(Long endTimeInSeconds) {
        this.endTimeInSeconds = endTimeInSeconds;
    }
}
