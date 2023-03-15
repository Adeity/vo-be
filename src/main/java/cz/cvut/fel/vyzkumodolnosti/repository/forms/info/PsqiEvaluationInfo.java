package cz.cvut.fel.vyzkumodolnosti.repository.forms.info;

public interface PsqiEvaluationInfo {
    Integer getId();

    String getAverageLaydownTime();

    Integer getMinutesToFallAsleep();

    String getAverageTimeOfGettingUp();

    Integer getPsqidurat();

    Integer getPsqidistb();

    Integer getPsqilaten();

    Integer getPsqidaydys();

    Integer getPsqihse();

    Integer getPsqislpqual();

    Integer getPsqimeds();

    Integer getPsqitotal();

    String getSleepDurationFreeDays();

    String getSleepDurationWorkDays();

    String getMidSleepFreeDays();

    String getMidSleepWorkDays();

    String getSJL();
}
