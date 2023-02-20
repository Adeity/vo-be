package cz.cvut.fel.vyzkumodolnosti.model.dto.forms.submitted;


import cz.cvut.fel.vyzkumodolnosti.model.dto.forms.variables.PsqiComputationVariablesDto;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class PsqiSubmittedFormDto extends SubmittedFormDto {
    @NotNull
    @Valid
    private PsqiComputationVariablesDto computationVariables;

    public PsqiComputationVariablesDto getComputationVariables() {
        return computationVariables;
    }

    public void setVariables(PsqiComputationVariablesDto variables) {
        this.computationVariables = variables;
    }
}
