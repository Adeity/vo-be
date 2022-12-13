package cz.cvut.fel.vyzkumodolnosti.services.forms.api;

import cz.cvut.fel.vyzkumodolnosti.model.dto.forms.variables.LifeSatisfactionComputationVariablesDto;
import cz.cvut.fel.vyzkumodolnosti.model.dto.forms.variables.MctqComputationVariablesDto;
import cz.cvut.fel.vyzkumodolnosti.model.dto.forms.variables.MeqComputationVariablesDto;
import cz.cvut.fel.vyzkumodolnosti.model.dto.forms.variables.PsqiComputationVariablesDto;
import cz.cvut.fel.vyzkumodolnosti.model.entities.forms.evaluations.LifeSatisfactionEvaluation;
import cz.cvut.fel.vyzkumodolnosti.model.entities.forms.evaluations.MctqEvaluation;
import cz.cvut.fel.vyzkumodolnosti.model.entities.forms.evaluations.MeqEvaluation;
import cz.cvut.fel.vyzkumodolnosti.model.entities.forms.evaluations.PsqiEvaluation;

public interface ComputationVariablesEvaluator {
    PsqiEvaluation evaluate(PsqiComputationVariablesDto variablesDto);

    MctqEvaluation evaluate(MctqComputationVariablesDto variablesDto);

    LifeSatisfactionEvaluation evaluate(LifeSatisfactionComputationVariablesDto variablesDto);

    MeqEvaluation evaluate(MeqComputationVariablesDto variablesDto);
}
