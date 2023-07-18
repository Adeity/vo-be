package cz.cvut.fel.vyzkumodolnosti.model.dto.forms.evaluation;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
@Getter
@Setter
public class MctqEvaluationXlsDto {

    private String SOw;
    private String GUw;
    private String SDw;
    private String TBTw;
    private String MSW;
    private String SOf;
    private String GUf;
    private String SDf;
    private String TBTf;
    private String MSF;
    private String SDweek;
    private String MSFsc;
    private String SLossweek;
    private String SJLrel;
    private String SJL;
    private String LEweek;

    private LocalDate mctqCreated;
}
