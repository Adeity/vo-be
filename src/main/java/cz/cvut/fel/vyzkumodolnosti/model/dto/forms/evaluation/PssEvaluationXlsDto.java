package cz.cvut.fel.vyzkumodolnosti.model.dto.forms.evaluation;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class PssEvaluationXlsDto {
    private Integer pssSum;
    private Integer pssNeg;
    private Integer pssPos;

    private LocalDate pssCreated;
}
