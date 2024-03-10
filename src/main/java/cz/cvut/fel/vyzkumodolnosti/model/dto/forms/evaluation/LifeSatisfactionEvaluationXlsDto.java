package cz.cvut.fel.vyzkumodolnosti.model.dto.forms.evaluation;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class LifeSatisfactionEvaluationXlsDto {
    private Integer ZDR;
    private Integer PRZ;
    private Integer FIN;
    private Integer VOL;
    private Integer MAN;
    private Integer DET;
    private Integer VLO;
    private Integer SEX;
    private Integer PZP;
    private Integer BYD;
    private Integer SUM;
    private Boolean hasKids;
    private Boolean hasPartner;

    private LocalDate lifeSatCreated;
}
