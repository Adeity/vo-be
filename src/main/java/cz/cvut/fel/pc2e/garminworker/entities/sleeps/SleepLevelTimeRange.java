package cz.cvut.fel.pc2e.garminworker.entities.sleeps;

import com.fasterxml.jackson.annotation.JsonProperty;
import cz.cvut.fel.pc2e.garminworker.entities.AbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Entity
@Data
public class SleepLevelTimeRange extends AbstractEntity {
    private Long startTimeInSeconds;

    private Long endTimeInSeconds;
}
