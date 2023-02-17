package cz.cvut.fel.vyzkumodolnosti.model.entities.forms.evaluations;

import cz.cvut.fel.vyzkumodolnosti.model.entities.AbstractEntity;
import cz.cvut.fel.vyzkumodolnosti.model.entities.forms.submitted.PsqiSubmittedForm;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Table(name = "psqi_evaluation")
public class PsqiEvaluation extends AbstractEntity {
    @NotNull
    @OneToOne
    @JoinColumn(name = "psqi_submitted_form_id", referencedColumnName = "id")
    private PsqiSubmittedForm submittedForm;
    @NotNull
    @Column(name = "avg_laydown_time", nullable = false)
    private String averageLaydownTime;
    @NotNull
    @Column(name = "minutes_to_fall_asleep", nullable = false)
    private Integer minutesToFallAsleep;
    @NotNull
    @Column(name = "avg_time_of_getting_up", nullable = false)
    private String averageTimeOfGettingUp;
    @NotNull
    @Column(name = "psqidurat", nullable = false)
    private Integer psqidurat;
    @NotNull
    @Column(name = "psqidistb", nullable = false)
    private Integer psqidistb;
    @NotNull
    @Column(name = "psqilaten", nullable = false)
    private Integer psqilaten;
    @NotNull
    @Column(name = "psqidaydys", nullable = false)
    private Integer psqidaydys;
    @NotNull
    @Column(name = "psqihse", nullable = false)
    private Integer psqihse;
    @NotNull
    @Column(name = "psqislpqual", nullable = false)
    private Integer psqislpqual;
    @NotNull
    @Column(name = "psqimeds", nullable = false)
    private Integer psqimeds;
    @NotNull
    @Column(name = "psqitotal", nullable = false)
    private Integer psqitotal;
    @Column(name = "sleep_duration_free_days", nullable = false) // (a) Délka spánku o volných dnech:
    private String sleepDurationFreeDays;
    @Column(name = "sleep_duration_work_days", nullable = false) // (b) Délka spánku v pracovních dnech:
    private String sleepDurationWorkDays;
    @Column(name = "mid_sleep_free_days", nullable = false) // (c) Střed spánku o volných dnech: (SOf + SDf/2)
    private String midSleepFreeDays;
    @Column(name = "mid_sleep_work_days", nullable = false) // (d) Střed spánku v pracovních dnech: (SOw + SDw/2)
    private String midSleepWorkDays;
    @Column(name = "sjl", nullable = false) // (e) Sociální JetLag:  Střed spánku volné dny - střed spánku pracovní dny = HH:MM (v absl.hodnotě).
    private String SJL;

    public PsqiSubmittedForm getSubmittedForm() {
        return submittedForm;
    }

    public void setSubmittedForm(PsqiSubmittedForm submittedForm) {
        this.submittedForm = submittedForm;
    }

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

    public String getSleepDurationFreeDays() {
        return sleepDurationFreeDays;
    }

    public void setSleepDurationFreeDays(String sleepDurationFreeDays) {
        this.sleepDurationFreeDays = sleepDurationFreeDays;
    }

    public String getSleepDurationWorkDays() {
        return sleepDurationWorkDays;
    }

    public void setSleepDurationWorkDays(String sleepDurationWorkDays) {
        this.sleepDurationWorkDays = sleepDurationWorkDays;
    }

    public String getMidSleepFreeDays() {
        return midSleepFreeDays;
    }

    public void setMidSleepFreeDays(String midSleepFreeDays) {
        this.midSleepFreeDays = midSleepFreeDays;
    }

    public String getMidSleepWorkDays() {
        return midSleepWorkDays;
    }

    public void setMidSleepWorkDays(String midSleepWorkDays) {
        this.midSleepWorkDays = midSleepWorkDays;
    }

    public String getSJL() {
        return SJL;
    }

    public void setSJL(String SJL) {
        this.SJL = SJL;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PsqiEvaluation that = (PsqiEvaluation) o;
        return Objects.equals(averageLaydownTime, that.averageLaydownTime) && Objects.equals(minutesToFallAsleep, that.minutesToFallAsleep) && Objects.equals(averageTimeOfGettingUp, that.averageTimeOfGettingUp) && Objects.equals(psqidurat, that.psqidurat) && Objects.equals(psqidistb, that.psqidistb) && Objects.equals(psqilaten, that.psqilaten) && Objects.equals(psqidaydys, that.psqidaydys) && Objects.equals(psqihse, that.psqihse) && Objects.equals(psqislpqual, that.psqislpqual) && Objects.equals(psqimeds, that.psqimeds) && Objects.equals(psqitotal, that.psqitotal) && Objects.equals(sleepDurationFreeDays, that.sleepDurationFreeDays) && Objects.equals(sleepDurationWorkDays, that.sleepDurationWorkDays) && Objects.equals(midSleepFreeDays, that.midSleepFreeDays) && Objects.equals(midSleepWorkDays, that.midSleepWorkDays) && Objects.equals(SJL, that.SJL);
    }

    @Override
    public int hashCode() {
        return Objects.hash(averageLaydownTime, minutesToFallAsleep, averageTimeOfGettingUp, psqidurat, psqidistb, psqilaten, psqidaydys, psqihse, psqislpqual, psqimeds, psqitotal);
    }
}
