package cz.cvut.fel.vyzkumodolnosti.model.dto.forms.submitted;

import cz.cvut.fel.vyzkumodolnosti.model.dto.forms.variables.MeqComputationVariablesDto;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class MeqSubmittedFormDto extends SubmittedFormDto {

    @NotNull
    @Valid
    private MeqComputationVariablesDto variablesDto;

    public MeqComputationVariablesDto getVariablesDto() {
        return variablesDto;
    }

    public void setVariablesDto(MeqComputationVariablesDto variablesDto) {
        this.variablesDto = variablesDto;
    }
}
