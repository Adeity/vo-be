package cz.cvut.fel.vyzkumodolnosti.model.dto.forms.evaluation;

import javax.validation.constraints.NotNull;

public class PsqiEvaluationDto {
    @NotNull
    private String averageLaydownTime;
    @NotNull
    private Integer minutesToFallAsleep;
    @NotNull
    private String averageTimeOfGettingUp;
    @NotNull
    private Integer psqidurat;
    @NotNull
    private Integer psqidistb;
    @NotNull
    private Integer psqilaten;
    @NotNull
    private Integer psqidaydys;
    @NotNull
    private Integer psqihse;
    @NotNull
    private Integer psqislpqual;
    @NotNull
    private Integer psqimeds;
    @NotNull
    private Integer psqitotal;

    public String getAverageLaydownTime() {
        return averageLaydownTime;
    }

    public void setAverageLaydownTime(String averageLaydownTime) {
        this.averageLaydownTime = averageLaydownTime;
    }

    public Integer getMinutesToFallAsleep() {
        return minutesToFallAsleep;
    }

    public void setMinutesToFallAsleep(Integer minutesToFallAsleep) {
        this.minutesToFallAsleep = minutesToFallAsleep;
    }

    public String getAverageTimeOfGettingUp() {
        return averageTimeOfGettingUp;
    }

    public void setAverageTimeOfGettingUp(String averageTimeOfGettingUp) {
        this.averageTimeOfGettingUp = averageTimeOfGettingUp;
    }

    public Integer getPsqidurat() {
        return psqidurat;
    }

    public void setPsqidurat(Integer psqidurat) {
        this.psqidurat = psqidurat;
    }

    public Integer getPsqidistb() {
        return psqidistb;
    }

    public void setPsqidistb(Integer psqidistb) {
        this.psqidistb = psqidistb;
    }

    public Integer getPsqilaten() {
        return psqilaten;
    }

    public void setPsqilaten(Integer psqilaten) {
        this.psqilaten = psqilaten;
    }

    public Integer getPsqidaydys() {
        return psqidaydys;
    }

    public void setPsqidaydys(Integer psqidaydys) {
        this.psqidaydys = psqidaydys;
    }

    public Integer getPsqihse() {
        return psqihse;
    }

    public void setPsqihse(Integer psqihse) {
        this.psqihse = psqihse;
    }

    public Integer getPsqislpqual() {
        return psqislpqual;
    }

    public void setPsqislpqual(Integer psqislpqual) {
        this.psqislpqual = psqislpqual;
    }

    public Integer getPsqimeds() {
        return psqimeds;
    }

    public void setPsqimeds(Integer psqimeds) {
        this.psqimeds = psqimeds;
    }

    public Integer getPsqitotal() {
        return psqitotal;
    }

    public void setPsqitotal(Integer psqitotal) {
        this.psqitotal = psqitotal;
    }
}
