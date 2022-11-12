package cz.cvut.fel.vyzkumodolnosti.model.dto.forms.submitted;

import cz.cvut.fel.vyzkumodolnosti.model.dto.forms.variables.MctqComputationVariablesDto;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class MctqSubmittedFormDto extends SubmittedFormDto {

    @NotNull
    @Valid
    private MctqComputationVariablesDto variablesDto;

    public MctqComputationVariablesDto getVariablesDto() {
        return variablesDto;
    }

    public void setVariablesDto(MctqComputationVariablesDto variablesDto) {
        this.variablesDto = variablesDto;
    }
}
