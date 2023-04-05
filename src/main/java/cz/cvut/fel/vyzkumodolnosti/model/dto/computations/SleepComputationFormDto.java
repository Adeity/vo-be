package cz.cvut.fel.vyzkumodolnosti.model.dto.computations;

import cz.cvut.fel.vyzkumodolnosti.model.domain.computations.ChronoVsRythm;
import cz.cvut.fel.vyzkumodolnosti.model.domain.computations.ChronotypeEnum;
import cz.cvut.fel.vyzkumodolnosti.model.domain.computations.ImprovementEnum;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import java.time.LocalTime;
import java.util.Date;

@Data
@NoArgsConstructor
@Getter
@Setter
public class SleepComputationFormDto {

    private Long id;

    private String person_id;

    private ChronoVsRythm chronoFa;

    private ChronoVsRythm chronoWa;

    private ChronotypeEnum chronotype;
    private boolean isLatencyFAGreater;

    private boolean isSocJetlagGreater;

    private ImprovementEnum compComparison;

    private Date created;

    private Date modified;

    private String latencyFAGreaterText;
    private String socJetlagGreaterText;
    private String chronoFaText;
    private String chronoWaText;

    private LocalTime awakeFrom;
    private LocalTime awakeTo;
    private LocalTime sleepFrom;
    private LocalTime sleepTo;
}
