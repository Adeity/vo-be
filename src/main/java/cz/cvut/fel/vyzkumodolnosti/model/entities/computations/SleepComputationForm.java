package cz.cvut.fel.vyzkumodolnosti.model.entities.computations;

import cz.cvut.fel.vyzkumodolnosti.model.domain.computations.ChronoVsRythm;
import cz.cvut.fel.vyzkumodolnosti.model.domain.computations.ChronotypeEnum;

import javax.persistence.*;

@Entity
@Table(name = "sleep_computation_forms")
public class SleepComputationForm {



    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Long id;

    @Column(name="person_id")
    private Integer person_id;

    @Column(name="chrono_rythm_asleep")
    private ChronoVsRythm chronoFa;

    public Integer getPerson_id() {
        return person_id;
    }

    public void setPerson_id(Integer person_id) {
        this.person_id = person_id;
    }

    public ChronoVsRythm getChronoFa() {
        return chronoFa;
    }

    public void setChronoFa(ChronoVsRythm chronoFa) {
        this.chronoFa = chronoFa;
    }

    public ChronoVsRythm getChronoWa() {
        return chronoWa;
    }

    public void setChronoWa(ChronoVsRythm chronoWa) {
        this.chronoWa = chronoWa;
    }

    @Column(name="chrono_rythm_wake")
    private ChronoVsRythm chronoWa;

    @Column(name="chronotype")
    private ChronotypeEnum chronotype;
    @Column(name="latency_greater")
    private boolean isLatencyFAGreater;

    @Column(name="soc_jetlag_greater")
    private boolean isSocJetlagGreater;

    public ChronotypeEnum getChronotype() {
        return chronotype;
    }

    public void setChronotype(ChronotypeEnum chronotype) {
        this.chronotype = chronotype;
    }

    public boolean isLatencyFAGreater() {
        return isLatencyFAGreater;
    }

    public void setLatencyFAGreater(boolean latencyFAGreater) {
        isLatencyFAGreater = latencyFAGreater;
    }

    public boolean isSocJetlagGreater() {
        return isSocJetlagGreater;
    }

    public void setSocJetlagGreater(boolean socJetlagGreater) {
        isSocJetlagGreater = socJetlagGreater;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    public Long getId() {
        return id;
    }
}
