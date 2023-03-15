package cz.cvut.fel.vyzkumodolnosti.model.dto.forms.submitted;

import cz.cvut.fel.vyzkumodolnosti.model.dto.forms.variables.MeqComputationVariablesDto;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class MeqSubmittedFormDto extends SubmittedFormDto {

    @NotNull
    @Valid
    private MeqComputationVariablesDto computationVariables;

    public MeqComputationVariablesDto getComputationVariables() {
        return computationVariables;
    }

    public void setComputationVariables(MeqComputationVariablesDto computationVariables) {
        this.computationVariables = computationVariables;
    }
}
