package cz.cvut.fel.vyzkumodolnosti.model.dto.forms.evaluation;

import cz.cvut.fel.vyzkumodolnosti.model.entities.forms.submitted.MctqSubmittedForm;

import javax.validation.constraints.NotNull;

public class MctqEvaluationDto {
    @NotNull
    private MctqSubmittedForm submittedForm;
    @NotNull
    private Double SQw;
    @NotNull
    private Double GUw;
    @NotNull
    private Double SDw;
    @NotNull
    private Double TBTw;
    @NotNull
    private Double MSW;
    @NotNull
    private Double SDweek;
    @NotNull
    private Double MSFsc;
    @NotNull
    private Double SLossweek;
    @NotNull
    private Double SJLrel;
    @NotNull
    private Double SJL;
    @NotNull
    private Double LEweek;

    public MctqSubmittedForm getSubmittedForm() {
        return submittedForm;
    }

    public void setSubmittedForm(MctqSubmittedForm submittedForm) {
        this.submittedForm = submittedForm;
    }

    public Double getSQw() {
        return SQw;
    }

    public void setSQw(Double SQw) {
        this.SQw = SQw;
    }

    public Double getGUw() {
        return GUw;
    }

    public void setGUw(Double GUw) {
        this.GUw = GUw;
    }

    public Double getSDw() {
        return SDw;
    }

    public void setSDw(Double SDw) {
        this.SDw = SDw;
    }

    public Double getTBTw() {
        return TBTw;
    }

    public void setTBTw(Double TBTw) {
        this.TBTw = TBTw;
    }

    public Double getMSW() {
        return MSW;
    }

    public void setMSW(Double MSW) {
        this.MSW = MSW;
    }

    public Double getSDweek() {
        return SDweek;
    }

    public void setSDweek(Double SDweek) {
        this.SDweek = SDweek;
    }

    public Double getMSFsc() {
        return MSFsc;
    }

    public void setMSFsc(Double MSFsc) {
        this.MSFsc = MSFsc;
    }

    public Double getSLossweek() {
        return SLossweek;
    }

    public void setSLossweek(Double SLossweek) {
        this.SLossweek = SLossweek;
    }

    public Double getSJLrel() {
        return SJLrel;
    }

    public void setSJLrel(Double SJLrel) {
        this.SJLrel = SJLrel;
    }

    public Double getSJL() {
        return SJL;
    }

    public void setSJL(Double SJL) {
        this.SJL = SJL;
    }

    public Double getLEweek() {
        return LEweek;
    }

    public void setLEweek(Double LEweek) {
        this.LEweek = LEweek;
    }
}
