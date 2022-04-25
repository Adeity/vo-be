package cz.cvut.fel.pc2e.garminworker.entities.sleeps;

import cz.cvut.fel.pc2e.garminworker.entities.AbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class SleepLevelsMap extends AbstractEntity {
    @OneToMany(cascade = CascadeType.ALL)
    List<PhaseOfSleepList> phaseOfSleepList;
}
