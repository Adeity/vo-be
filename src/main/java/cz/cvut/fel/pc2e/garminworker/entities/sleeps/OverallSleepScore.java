package cz.cvut.fel.pc2e.garminworker.entities.sleeps;

import cz.cvut.fel.pc2e.garminworker.entities.AbstractEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Entity
@Data
@NoArgsConstructor
public class OverallSleepScore extends AbstractEntity {
    private Integer value;
    private String qualifierKey;
}
