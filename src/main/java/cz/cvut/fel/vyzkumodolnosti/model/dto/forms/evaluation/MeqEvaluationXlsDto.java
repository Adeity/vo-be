package cz.cvut.fel.vyzkumodolnosti.model.dto.forms.evaluation;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class MeqEvaluationXlsDto {

    private Integer meqValue;
    private LocalDate meqCreated;

}
