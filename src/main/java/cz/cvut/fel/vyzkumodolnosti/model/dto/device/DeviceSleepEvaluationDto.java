package cz.cvut.fel.vyzkumodolnosti.model.dto.device;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
public class DeviceSleepEvaluationDto {

    private String id;

    private Long version;

    private Double avgSleepWorkDays;

    private Double avgSleepFreeDays;

    private Double avgSleepWeek;

    private LocalTime avgFallAsleepTimeWorkDays;

    private LocalTime avgFallAsleepTimeFreeDays;

    private LocalTime avgWakingTimeWorkDays;

    private LocalTime avgWakingTimeFreeDays;

    private Double expectedSleepTimeWorkDays;

    private Double expectedSleepTimeFreeDays;

    private Double socJetlag;

    private LocalTime avgFallAsleepTimeWeek;

    private LocalTime avgWakingTimeWeek;

    private LocalDate created;
}
