package cz.cvut.fel.vyzkumodolnosti.model.entities.forms.evaluations;

import cz.cvut.fel.vyzkumodolnosti.model.entities.AbstractEntity;
import cz.cvut.fel.vyzkumodolnosti.model.entities.forms.submitted.LifeSatisfactionSubmittedForm;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "life_sat_evaluation")
public class LifeSatisfactionEvaluation extends AbstractEntity {
    @NotNull
    @OneToOne
    @JoinColumn(name = "life_sat_submitted_form_id", referencedColumnName = "id")
    private LifeSatisfactionSubmittedForm submittedForm;

    @NotNull
    @Column(name = "ZDR")
    private Integer ZDR;
    @NotNull
    @Column(name = "PRZ")
    private Integer PRZ;
    @NotNull
    @Column(name = "FIN")
    private Integer FIN;
    @NotNull
    @Column(name = "VOL")
    private Integer VOL;
    @Column(name = "MAN", nullable = true)
    private Integer MAN;
    @NotNull
    @Column(name = "DET", nullable = true)
    private Integer DET;
    @NotNull
    @Column(name = "VLO")
    private Integer VLO;
    @NotNull
    @Column(name = "SEX")
    private Integer SEX;
    @NotNull
    @Column(name = "PZP")
    private Integer PZP;
    @NotNull
    @Column(name = "BYD")
    private Integer BYD;
    @NotNull
    @Column(name = "SUM")
    private Integer SUM;

    public LifeSatisfactionSubmittedForm getSubmittedForm() {
        return submittedForm;
    }

    public void setSubmittedForm(LifeSatisfactionSubmittedForm submittedForm) {
        this.submittedForm = submittedForm;
    }

    public Integer getZDR() {
        return ZDR;
    }

    public void setZDR(Integer ZDR) {
        this.ZDR = ZDR;
    }

    public Integer getPRZ() {
        return PRZ;
    }

    public void setPRZ(Integer PRZ) {
        this.PRZ = PRZ;
    }

    public Integer getFIN() {
        return FIN;
    }

    public void setFIN(Integer FIN) {
        this.FIN = FIN;
    }

    public Integer getVOL() {
        return VOL;
    }

    public void setVOL(Integer VOL) {
        this.VOL = VOL;
    }

    public Integer getMAN() {
        return MAN;
    }

    public void setMAN(Integer MAN) {
        this.MAN = MAN;
    }

    public Integer getDET() {
        return DET;
    }

    public void setDET(Integer DET) {
        this.DET = DET;
    }

    public Integer getVLO() {
        return VLO;
    }

    public void setVLO(Integer VLO) {
        this.VLO = VLO;
    }

    public Integer getSEX() {
        return SEX;
    }

    public void setSEX(Integer SEX) {
        this.SEX = SEX;
    }

    public Integer getPZP() {
        return PZP;
    }

    public void setPZP(Integer PZP) {
        this.PZP = PZP;
    }

    public Integer getBYD() {
        return BYD;
    }

    public void setBYD(Integer BYD) {
        this.BYD = BYD;
    }

    public Integer getSUM() {
        return SUM;
    }

    public void setSUM(Integer SUM) {
        this.SUM = SUM;
    }
}
