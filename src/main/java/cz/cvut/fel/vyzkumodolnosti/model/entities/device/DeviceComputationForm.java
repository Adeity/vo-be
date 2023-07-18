package cz.cvut.fel.vyzkumodolnosti.model.entities.device;

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
@Table(name = "device_computation_forms")
@Getter
@Setter
public class DeviceComputationForm {

    public DeviceComputationForm(ResearchParticipant researchParticipant) {

        this.researchParticipant = researchParticipant;
    }

    public DeviceComputationForm() {

    }

    @Id
    @Column(name="id")
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "research_participant_research_number", referencedColumnName = "research_number")
    private ResearchParticipant researchParticipant;

    @Column(name="version")
    private Long version;

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
    @Column(name="chrono_rythm_asleep_work_days")
    private ChronoVsRythm chronoFaWorkDays;

    @Column(name="chrono_rythm_wake_work_days")
    private ChronoVsRythm chronoWaWorkDays;
    @Column(name="chrono_rythm_asleep_free_days")
    private ChronoVsRythm chronoFaFreeDays;

    @Column(name="chrono_rythm_wake_free_days")
    private ChronoVsRythm chronoWaFreeDays;

    @Column(name="chrono_rythm_asleep_text_work_days")
    private String chronoFaTextWorkDays;

    @Column(name="chrono_rythm_wake_text_work_days")
    private String chronoWaTextWorkDays;

    @Column(name="chrono_rythm_asleep_text_free_days")
    private String chronoFaTextFreeDays;

    @Column(name="chrono_rythm_wake_text_free_days")
    private String chronoWaTextFreeDays;

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
    private Date created;

    @Column(name="modified")
    private Date modified;

    @Column(name="avg_fall_asleep_time_work_days")
    private LocalTime avgFallAsleepTimeWorkDays;
    @Column(name="avg_waking_time_work_days")
    private LocalTime avgWakingTimeWorkDays;
    @Column(name="avg_fall_asleep_time_free_days")
    private LocalTime avgFallAsleepTimeFreeDays;
    @Column(name="avg_waking_time_free_days")
    private LocalTime avgWakingTimeFreeDays;

    @Column(name="awakeFrom")
    private LocalTime awakeFrom;
    @Column(name="awakeTo")
    private LocalTime awakeTo;
    @Column(name="sleepFrom")
    private LocalTime sleepFrom;
    @Column(name="sleepTo")
    private LocalTime sleepTo;

    public String getPersonId() {
        return this.researchParticipant.getResearchNumber();
    }

    @PrePersist
    private void sedCreatedAndModified() {
        this.created = new Date();
        this.modified = this.created;
    }

    @Override
    public String toString() {
        return
                "Id: " + this.id + "\n" +
                        "person id: " + this.researchParticipant.getResearchNumber() + "\n" +
                        "chronotype: " + this.chronotype + "\n" +
                        "chronoWaWorkDays: " +this.chronoWaWorkDays + "\n" +
                        "chronoFaWorkDays: " + this.chronoFaWorkDays + "\n" +
                        "chronoWaFreeDays: " +this.chronoWaFreeDays + "\n" +
                        "chronoFaFreeDays: " + this.chronoFaFreeDays + "\n" +
                        "latency FA greater: " + this.isLatencyFAGreater + "\n" +
                        "soc jetlag greater: " + this.isSocJetlagGreater + "\n";
    }
}
