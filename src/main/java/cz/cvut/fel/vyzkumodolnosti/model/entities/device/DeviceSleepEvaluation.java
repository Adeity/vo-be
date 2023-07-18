package cz.cvut.fel.vyzkumodolnosti.model.entities.device;

import cz.cvut.fel.vyzkumodolnosti.model.entities.AbstractEntity;
import cz.cvut.fel.vyzkumodolnosti.model.entities.ResearchParticipant;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "device_sleep_evaluation")
@Getter
@Setter
public class DeviceSleepEvaluation extends AbstractEntity {

    @NotNull
    @ManyToOne
    @JoinColumn(name = "research_participant_research_number", referencedColumnName = "research_number")
    private ResearchParticipant researchParticipant;

    @Column(name="version")
    private Long version;

    @Column(name="avg_sleep_work_days")
    private Double avgSleepWorkDays;

    @Column(name="avg_sleep_free_days")
    private Double avgSleepFreeDays;

    @Column(name="avg_fall_asleep_time_work_days")
    private LocalTime avgFallAsleepTimeWorkDays;

    @Column(name="avg_fall_asleep_time_free_days")
    private LocalTime avgFallAsleepTimeFreeDays;

    @Column(name="avg_waking_time_work_days")
    private LocalTime avgWakingTimeWorkDays;

    @Column(name="avg_waking_time_free_days")
    private LocalTime avgWakingTimeFreeDays;

    @Column(name="expected_sleep_time_work_days")
    private Double expectedSleepTimeWorkDays;

    @Column(name="expected_sleep_time_free_days")
    private Double expectedSleepTimeFreeDays;

    @NotNull
    @Column(name = "created")
    private LocalDate created;

    @PrePersist
    public void setCreatedPrePersist() {
        this.created = LocalDate.now();
    }

}
