package cz.cvut.fel.vyzkumodolnosti.model.entities.sleeps;

import cz.cvut.fel.vyzkumodolnosti.model.entities.AbstractEntity;
import cz.cvut.fel.vyzkumodolnosti.model.domain.sleeps.SleepPhaseEnum;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(
        name = "phase_of_sleep_list"
)
public class PhaseOfSleepList extends AbstractEntity {

    @NotNull
    @Column(
            name = "sleep_phase_enum"
    )
    private SleepPhaseEnum SleepPhaseEnum;
    @NotNull
    @OneToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<SleepLevelTimeRange> sleepLevelTimeRangeList;

    public PhaseOfSleepList() {
    }

    public PhaseOfSleepList(cz.cvut.fel.vyzkumodolnosti.model.domain.sleeps.SleepPhaseEnum sleepPhaseEnum,
                            List<SleepLevelTimeRange> sleepLevelTimeRangeList) {
        SleepPhaseEnum = sleepPhaseEnum;
        this.sleepLevelTimeRangeList = sleepLevelTimeRangeList;
    }

    public cz.cvut.fel.vyzkumodolnosti.model.domain.sleeps.SleepPhaseEnum getSleepPhaseEnum() {
        return SleepPhaseEnum;
    }

    public void setSleepPhaseEnum(cz.cvut.fel.vyzkumodolnosti.model.domain.sleeps.SleepPhaseEnum sleepPhaseEnum) {
        SleepPhaseEnum = sleepPhaseEnum;
    }

    public List<SleepLevelTimeRange> getSleepLevelTimeRangeList() {
        return sleepLevelTimeRangeList;
    }

    public void setSleepLevelTimeRangeList(List<SleepLevelTimeRange> sleepLevelTimeRangeList) {
        this.sleepLevelTimeRangeList = sleepLevelTimeRangeList;
    }
}
