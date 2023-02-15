package cz.cvut.fel.vyzkumodolnosti.model.dto.forms.submitted;

import cz.cvut.fel.vyzkumodolnosti.model.dto.forms.variables.PssComputationVariablesDto;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class PssSubmittedFormDto extends SubmittedFormDto {

    @NotNull
    @Valid
    private PssComputationVariablesDto variablesDto;

    public PssComputationVariablesDto getVariablesDto() {
        return variablesDto;
    }

    public void setVariablesDto(PssComputationVariablesDto variablesDto) {
        this.variablesDto = variablesDto;
    }
}
