package cz.cvut.fel.vyzkumodolnosti.model.entities.forms.evaluations;

import cz.cvut.fel.vyzkumodolnosti.model.entities.AbstractEntity;
import cz.cvut.fel.vyzkumodolnosti.model.entities.forms.submitted.MctqSubmittedForm;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "mctq_evaluation")
public class MctqEvaluation extends AbstractEntity {

    @NotNull
    @OneToOne
    @JoinColumn(name = "mctq_submitted_form_id", referencedColumnName = "id")
    private MctqSubmittedForm submittedForm;

    // Computed variables workdays
    @NotNull
    @Column(name = "sow")
    private String SOw;
    @NotNull
    @Column(name = "guw")
    private String GUw;
    @NotNull
    @Column(name = "sdw")
    private String SDw;
    @NotNull
    @Column(name = "tbtw")
    private String TBTw;
    @NotNull
    @Column(name = "msw")
    private String MSW;

    // Computed variables work-free days
    @NotNull
    @Column(name = "sof")
    private String SOf;
    @NotNull
    @Column(name = "guf")
    private String GUf;
    @NotNull
    @Column(name = "sdf")
    private String SDf;
    @NotNull
    @Column(name = "tbtf")
    private String TBTf;
    @NotNull
    @Column(name = "msf")
    private String MSF;

    // Computed variables combining workdays and work-free days
    @NotNull
    @Column(name = "sdweek")
    private String SDweek;
    @NotNull
    @Column(name = "msfsc")
    private String MSFsc;
    @NotNull
    @Column(name = "slossweek")
    private String SLossweek;
    @NotNull
    @Column(name = "sjlrel")
    private String SJLrel;
    @NotNull
    @Column(name = "sjl")
    private String SJL;
    @NotNull
    @Column(name = "leweek")
    private String LEweek;

    public MctqSubmittedForm getSubmittedForm() {
        return submittedForm;
    }

    public void setSubmittedForm(MctqSubmittedForm submittedForm) {
        this.submittedForm = submittedForm;
    }

    public String getSOw() {
        return SOw;
    }

    public void setSOw(String SOw) {
        this.SOw = SOw;
    }

    public String getGUw() {
        return GUw;
    }

    public void setGUw(String GUw) {
        this.GUw = GUw;
    }

    public String getSDw() {
        return SDw;
    }

    public void setSDw(String SDw) {
        this.SDw = SDw;
    }

    public String getTBTw() {
        return TBTw;
    }

    public void setTBTw(String TBTw) {
        this.TBTw = TBTw;
    }

    public String getMSW() {
        return MSW;
    }

    public void setMSW(String MSW) {
        this.MSW = MSW;
    }

    public String getSOf() {
        return SOf;
    }

    public void setSOf(String SOf) {
        this.SOf = SOf;
    }

    public String getGUf() {
        return GUf;
    }

    public void setGUf(String GUf) {
        this.GUf = GUf;
    }

    public String getSDf() {
        return SDf;
    }

    public void setSDf(String SDf) {
        this.SDf = SDf;
    }

    public String getTBTf() {
        return TBTf;
    }

    public void setTBTf(String TBTf) {
        this.TBTf = TBTf;
    }

    public String getMSF() {
        return MSF;
    }

    public void setMSF(String MSF) {
        this.MSF = MSF;
    }

    public String getSDweek() {
        return SDweek;
    }

    public void setSDweek(String SDweek) {
        this.SDweek = SDweek;
    }

    public String getMSFsc() {
        return MSFsc;
    }

    public void setMSFsc(String MSFsc) {
        this.MSFsc = MSFsc;
    }

    public String getSLossweek() {
        return SLossweek;
    }

    public void setSLossweek(String SLossweek) {
        this.SLossweek = SLossweek;
    }

    public String getSJLrel() {
        return SJLrel;
    }

    public void setSJLrel(String SJLrel) {
        this.SJLrel = SJLrel;
    }

    public String getSJL() {
        return SJL;
    }

    public void setSJL(String SJL) {
        this.SJL = SJL;
    }

    public String getLEweek() {
        return LEweek;
    }

    public void setLEweek(String LEweek) {
        this.LEweek = LEweek;
    }
}
