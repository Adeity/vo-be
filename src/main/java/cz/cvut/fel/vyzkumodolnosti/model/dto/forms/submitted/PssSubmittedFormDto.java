package cz.cvut.fel.vyzkumodolnosti.model.dto.forms.submitted;

import cz.cvut.fel.vyzkumodolnosti.model.dto.forms.variables.PssComputationVariablesDto;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class PssSubmittedFormDto extends SubmittedFormDto {

    @NotNull
    @Valid
    private PssComputationVariablesDto computationVariables;

    public PssComputationVariablesDto getComputationVariables() {
        return computationVariables;
    }

    public void setComputationVariables(PssComputationVariablesDto computationVariables) {
        this.computationVariables = computationVariables;
    }
}
