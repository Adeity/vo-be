package cz.cvut.fel.vyzkumodolnosti.model.dto.forms.submitted;

import cz.cvut.fel.vyzkumodolnosti.model.dto.forms.variables.MctqComputationVariablesDto;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class MctqSubmittedFormDto extends SubmittedFormDto {

    @NotNull
    @Valid
    private MctqComputationVariablesDto computationVariables;

    public MctqComputationVariablesDto getComputationVariables() {
        return computationVariables;
    }

    public void setComputationVariables(MctqComputationVariablesDto computationVariables) {
        this.computationVariables = computationVariables;
    }
}
