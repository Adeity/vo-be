package cz.cvut.fel.vyzkumodolnosti.model.entities.sleeps;

import cz.cvut.fel.vyzkumodolnosti.model.entities.AbstractEntity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
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
