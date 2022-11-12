package cz.cvut.fel.vyzkumodolnosti.model.dto.forms.submitted;

import cz.cvut.fel.vyzkumodolnosti.model.dto.forms.variables.LifeSatisfactionComputationVariablesDto;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class LifeSatisfactionSubmittedFormDto extends SubmittedFormDto {

    @NotNull
    @Valid
    private LifeSatisfactionComputationVariablesDto variablesDto;

    public LifeSatisfactionComputationVariablesDto getVariablesDto() {
        return variablesDto;
    }

    public void setVariablesDto(LifeSatisfactionComputationVariablesDto variablesDto) {
        this.variablesDto = variablesDto;
    }
}
