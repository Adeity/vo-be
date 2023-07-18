package cz.cvut.fel.vyzkumodolnosti.model.entities.computations;

import cz.cvut.fel.vyzkumodolnosti.model.domain.computations.ChronoVsRythm;
import cz.cvut.fel.vyzkumodolnosti.model.domain.computations.ChronotypeEnum;
import cz.cvut.fel.vyzkumodolnosti.model.domain.computations.ImprovementEnum;
import cz.cvut.fel.vyzkumodolnosti.model.entities.ResearchParticipant;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.Date;

@Entity
@Table(name = "sleep_computation_forms")
@Getter
@Setter
public class SleepComputationForm {

    public SleepComputationForm(ResearchParticipant researchParticipant) {

        this.researchParticipant = researchParticipant;
    }

    public SleepComputationForm() {

    }

    @Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "research_participant_research_number", referencedColumnName = "research_number")
    private ResearchParticipant researchParticipant;

    @Column(name="chrono_rythm_asleep")
    private ChronoVsRythm chronoFa;

    @Column(name="version")
    private Integer version;

    @Column(name="recalculations")
    private Integer recalculations;
    @Column(name="latency")
    private Integer latency;
    @Column(name="socJetlag")
    private String socJetlag;
    @Column(name="soc_jetlag_threshold")
    private LocalTime socJetlagThreshold;

    @Column(name="latency_fa_threshold")
    private Integer latencyFaThreshold;

    @Column(name="chrono_rythm_wake")
    private ChronoVsRythm chronoWa;

    @Column(name="chrono_rythm_asleep_text")
    private String chronoFaText;

    @Column(name="chrono_rythm_wake_text")
    private String chronoWaText;

    @Column(name="chronotype")
    private ChronotypeEnum chronotype;
    @Column(name="latency_greater")
    private boolean isLatencyFAGreater;

    @Column(name="latency_greater_text")
    private String latencyFAGreaterText;

    @Column(name="soc_jetlag_greater")
    private boolean isSocJetlagGreater;

    @Column(name="soc_jetlag_greater_text")
    private String socJetlagGreaterText;

    @Column(name="comparison")
    private ImprovementEnum compComparison = ImprovementEnum.NO_COMPARE;

    @Column(name="created")
    private Date created = new Date();

    @Column(name="modified")
    private Date modified = new Date();

    @Column(name="avg_laydown_time")
    private String avgLaydownTime;
    @Column(name="avg_waking_time")
    private String avgWakingTime;

    @Column(name="awakeFrom")
    private LocalTime awakeFrom;
    @Column(name="awakeTo")
    private LocalTime awakeTo;
    @Column(name="sleepFrom")
    private LocalTime sleepFrom;
    @Column(name="sleepTo")
    private LocalTime sleepTo;

    @Override
    public String toString() {
        return
                "Id: " + this.id + "\n" +
                "person id" + this.researchParticipant.getResearchNumber() + "\n" +
                "chronotype: " + this.chronotype + "\n" +
                "chronoWa: " +this.chronoWa + "\n" +
                "chronoFa: " + this.chronoFa + "\n" +
                "latency FA greater: " + this.isLatencyFAGreater + "\n" +
                "soc jetlag greater: " + this.isSocJetlagGreater + "\n";
    }

    public String getPersonId() {
        return this.researchParticipant.getResearchNumber();
    }
}
