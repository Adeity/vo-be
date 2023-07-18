package cz.cvut.fel.vyzkumodolnosti.model.dto.device;

import cz.cvut.fel.vyzkumodolnosti.model.domain.computations.ChronoVsRythm;
import cz.cvut.fel.vyzkumodolnosti.model.domain.computations.ChronotypeEnum;
import cz.cvut.fel.vyzkumodolnosti.model.domain.computations.ImprovementEnum;
import cz.cvut.fel.vyzkumodolnosti.model.entities.ResearchParticipant;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.Pattern;
import java.time.LocalTime;
import java.util.Date;
@Getter
@Setter
public class DeviceComputationFormDto {

    private Long id;
    @Pattern(regexp = "^[a-zA-Z0-9]{3}_[a-zA-Z0-9]{3}$")
    private String researchParticipantResearchNumber;
    private Long version;
    private Integer recalculations;

    private Integer latency;
    private String socJetlag;
    private String socJetlagThreshold;

    private Integer latencyFaThreshold;

    private ChronoVsRythm chronoFaWorkDays;

    private ChronoVsRythm chronoWaWorkDays;
    private ChronoVsRythm chronoFaFreeDays;

    private ChronoVsRythm chronoWaFreeDays;

    private String chronoFaTextWorkDays;

    private String chronoWaTextWorkDays;

    private String chronoFaTextFreeDays;

    private String chronoWaTextFreeDays;

    private ChronotypeEnum chronotype;
    private boolean isLatencyFAGreater;

    private String latencyFAGreaterText;

    private boolean isSocJetlagGreater;

    private String socJetlagGreaterText;

    private ImprovementEnum compComparison;

    private Date created;

    private Date modified;

    @Pattern(regexp = "^[a-zA-Z0-9]{3}_[a-zA-Z0-9]{3}$")
    private String avgFallAsleepTimeWorkDays;
    @Pattern(regexp = "^[a-zA-Z0-9]{3}_[a-zA-Z0-9]{3}$")
    private String avgWakingTimeWorkDays;
    @Pattern(regexp = "^[a-zA-Z0-9]{3}_[a-zA-Z0-9]{3}$")
    private String avgFallAsleepTimeFreeDays;
    @Pattern(regexp = "^[a-zA-Z0-9]{3}_[a-zA-Z0-9]{3}$")
    private String avgWakingTimeFreeDays;

    private LocalTime awakeFrom;
    private LocalTime awakeTo;
    private LocalTime sleepFrom;
    private LocalTime sleepTo;
}
