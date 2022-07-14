package cz.cvut.fel.pc2e.garminworker.entities.sleeps;

import cz.cvut.fel.pc2e.garminworker.entities.AbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(
        name = "sleep_levels_map"
)
public class SleepLevelsMap extends AbstractEntity {
    @OneToMany(cascade = CascadeType.ALL)
    List<PhaseOfSleepList> phaseOfSleepList;

    public SleepLevelsMap() {
    }

    public SleepLevelsMap(List<PhaseOfSleepList> phaseOfSleepList) {
        this.phaseOfSleepList = phaseOfSleepList;
    }

    public List<PhaseOfSleepList> getPhaseOfSleepList() {
        return phaseOfSleepList;
    }

    public void setPhaseOfSleepList(List<PhaseOfSleepList> phaseOfSleepList) {
        this.phaseOfSleepList = phaseOfSleepList;
    }
}
