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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PsqiEvaluation that = (PsqiEvaluation) o;
        return averageLaydownTime.equals(that.averageLaydownTime) && minutesToFallAsleep.equals(that.minutesToFallAsleep) && averageTimeOfGettingUp.equals(that.averageTimeOfGettingUp) && psqidurat.equals(that.psqidurat) && psqidistb.equals(that.psqidistb) && psqilaten.equals(that.psqilaten) && psqidaydys.equals(that.psqidaydys) && psqihse.equals(that.psqihse) && psqislpqual.equals(that.psqislpqual) && psqimeds.equals(that.psqimeds) && psqitotal.equals(that.psqitotal);
    }

    @Override
    public int hashCode() {
        return Objects.hash(averageLaydownTime, minutesToFallAsleep, averageTimeOfGettingUp, psqidurat, psqidistb, psqilaten, psqidaydys, psqihse, psqislpqual, psqimeds, psqitotal);
    }
}
