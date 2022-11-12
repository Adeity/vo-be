package cz.cvut.fel.vyzkumodolnosti.model.dto.forms.submitted;

import cz.cvut.fel.vyzkumodolnosti.model.dto.forms.variables.LifeSatisfactionComputationVariablesDto;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class LifeSatisfactionSubmittedFormDto extends SubmittedFormDto {

    @NotNull
    @Valid
    private LifeSatisfactionComputationVariablesDto computationVariables;

    public LifeSatisfactionComputationVariablesDto getComputationVariables() {
        return computationVariables;
    }

    public void setComputationVariables(LifeSatisfactionComputationVariablesDto computationVariables) {
        this.computationVariables = computationVariables;
    }
}
