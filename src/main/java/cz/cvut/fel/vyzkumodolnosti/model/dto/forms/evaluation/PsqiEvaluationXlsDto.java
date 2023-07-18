package cz.cvut.fel.vyzkumodolnosti.model.dto.forms.evaluation;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class PsqiEvaluationXlsDto {

    private String averageLaydownTime;
    private int minutesToFallAsleep;
    private String averageTimeOfGettingUp;
    private int psqidurat;
    private int psqidistb;
    private int psqilaten;
    private int psqidaydys;
    private int psqihse;
    private int psqislpqual;
    private int psqimeds;
    private int psqitotal;
    private String sleepDurationFreeDays;
    private String sleepDurationWorkDays;
    private String midSleepFreeDays;
    private String midSleepWorkDays;
    private String SJL;

    private LocalDate psqiCreated;
}
