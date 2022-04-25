package cz.cvut.fel.pc2e.garminworker.entities.sleeps;

import cz.cvut.fel.pc2e.garminworker.entities.AbstractEntity;
import cz.cvut.fel.pc2e.garminworker.entities.sleeps.SleepLevelTimeRange;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class PhaseOfSleepList extends AbstractEntity {

    private SleepPhaseEnum SleepPhaseEnum;
    @OneToMany(cascade = CascadeType.ALL)
    private List<SleepLevelTimeRange> sleepLevelTimeRangeList;
}
