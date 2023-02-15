package cz.cvut.fel.vyzkumodolnosti.services.forms.api;

import cz.cvut.fel.vyzkumodolnosti.model.dto.forms.variables.*;
import cz.cvut.fel.vyzkumodolnosti.model.entities.forms.evaluations.*;

public interface ComputationVariablesEvaluator {
    PsqiEvaluation evaluate(PsqiComputationVariablesDto variablesDto);

    MctqEvaluation evaluate(MctqComputationVariablesDto variablesDto);

    LifeSatisfactionEvaluation evaluate(LifeSatisfactionComputationVariablesDto variablesDto);

    MeqEvaluation evaluate(MeqComputationVariablesDto variablesDto);

    PssEvaluation evaluate(PssComputationVariablesDto variablesDto);
}
