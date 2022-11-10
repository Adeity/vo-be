package cz.cvut.fel.vyzkumodolnosti.model.dto.forms.evaluation;

import javax.validation.constraints.NotNull;

public class LifeSatisfactionEvaluationDto {

    @NotNull
    private Integer ZDR;
    @NotNull
    private Integer PRZ;
    @NotNull
    private Integer FIN;
    @NotNull
    private Integer VOL;
    private Integer MAN;
    private Integer DET;
    @NotNull
    private Integer VLO;
    @NotNull
    private Integer SEX;
    @NotNull
    private Integer PZP;
    @NotNull
    private Integer BYD;
    @NotNull
    private Integer SUM;
}
