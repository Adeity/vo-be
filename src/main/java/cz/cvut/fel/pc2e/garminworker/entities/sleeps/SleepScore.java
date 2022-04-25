package cz.cvut.fel.pc2e.garminworker.entities.sleeps;

import cz.cvut.fel.pc2e.garminworker.entities.AbstractEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Entity
@Data
@NoArgsConstructor
public class SleepScore extends AbstractEntity {
    private String sleepScore;
    private String qualifierKey;
}
