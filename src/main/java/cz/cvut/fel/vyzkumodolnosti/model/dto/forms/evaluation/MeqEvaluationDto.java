package cz.cvut.fel.vyzkumodolnosti.model.dto.forms.evaluation;

import javax.validation.constraints.NotNull;

public class MeqEvaluationDto {
    @NotNull
    private Integer meqValue;

    public Integer getMeqValue() {
        return meqValue;
    }

    public void setMeqValue(Integer meqValue) {
        this.meqValue = meqValue;
    }
}
