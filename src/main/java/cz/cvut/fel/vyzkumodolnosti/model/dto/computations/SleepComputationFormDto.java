package cz.cvut.fel.vyzkumodolnosti.model.dto.computations;

import cz.cvut.fel.vyzkumodolnosti.model.domain.computations.ChronoVsRythm;
import cz.cvut.fel.vyzkumodolnosti.model.domain.computations.ChronotypeEnum;
import cz.cvut.fel.vyzkumodolnosti.model.domain.computations.ImprovementEnum;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Pattern;
import java.time.LocalTime;
import java.util.Date;

@Data
@NoArgsConstructor
@Getter
@Setter
public class SleepComputationFormDto {

    private Long id;

    private Integer version;
    private Integer recalculations;
    @Pattern(regexp = "^[a-zA-Z0-9]{3}_[a-zA-Z0-9]{3}$")
    private String person_id;
    private String socJetlagThreshold;
    @Pattern(regexp = "^([0-1]?[0-9]|2[0-3]):[0-5][0-9]$")
    private Integer latencyFaThreshold;
    private ChronoVsRythm chronoFa;
    private ChronoVsRythm chronoWa;
    private ChronotypeEnum chronotype;
    private Integer latency;
    private String socJetlag;
    private boolean isLatencyFAGreater;
    private boolean isSocJetlagGreater;
    private ImprovementEnum compComparison;
    private Date created;
    private Date modified;
    private String latencyFAGreaterText;
    private String socJetlagGreaterText;
    private String chronoFaText;
    private String chronoWaText;
    @Pattern(regexp = "^([0-1]?[0-9]|2[0-3]):[0-5][0-9]$")
    private String avgLaydownTime;
    @Pattern(regexp = "^([0-1]?[0-9]|2[0-3]):[0-5][0-9]$")
    private String avgWakingTime;
    private LocalTime awakeFrom;
    private LocalTime awakeTo;
    private LocalTime sleepFrom;
    private LocalTime sleepTo;
}
