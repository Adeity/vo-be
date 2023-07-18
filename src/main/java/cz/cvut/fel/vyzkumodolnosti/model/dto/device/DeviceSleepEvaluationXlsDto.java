package cz.cvut.fel.vyzkumodolnosti.model.dto.device;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
public class DeviceSleepEvaluationXlsDto {

    private String researchNumber;

    // in hours
    private double avgSleepWorkDays;

    // in hours
    private double avgSleepFreeDays;

    private LocalTime avgFallAsleepTimeWorkDays;

    private LocalTime avgFallAsleepTimeFreeDays;

    private LocalTime avgWakingTimeWorkDays;

    private LocalTime avgWakingTimeFreeDays;

    private LocalTime expectedSleepTimeWorkDays;

    private LocalTime expectedSleepTimeFreeDays;

    private LocalDate created;
}
