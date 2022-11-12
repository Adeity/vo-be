package cz.cvut.fel.vyzkumodolnosti.services.forms.impl;

import cz.cvut.fel.vyzkumodolnosti.model.dto.forms.variables.LifeSatisfactionComputationVariablesDto;
import cz.cvut.fel.vyzkumodolnosti.model.dto.forms.variables.MctqComputationVariablesDto;
import cz.cvut.fel.vyzkumodolnosti.model.dto.forms.variables.MeqComputationVariablesDto;
import cz.cvut.fel.vyzkumodolnosti.model.dto.forms.variables.PsqiComputationVariablesDto;
import cz.cvut.fel.vyzkumodolnosti.model.entities.forms.evaluations.LifeSatisfactionEvaluation;
import cz.cvut.fel.vyzkumodolnosti.model.entities.forms.evaluations.MctqEvaluation;
import cz.cvut.fel.vyzkumodolnosti.model.entities.forms.evaluations.MeqEvaluation;
import cz.cvut.fel.vyzkumodolnosti.model.entities.forms.evaluations.PsqiEvaluation;
import cz.cvut.fel.vyzkumodolnosti.services.forms.api.ComputationVariablesEvaluator;
import org.springframework.stereotype.Service;

@Service
public class ComputationVariablesEvaluatorImpl implements ComputationVariablesEvaluator {
    @Override
    public PsqiEvaluation evaluate(PsqiComputationVariablesDto variablesDto) {
        throw new RuntimeException("Not yet implemented!");
    }

    @Override
    public MctqEvaluation evaluate(MctqComputationVariablesDto variablesDto) {
        throw new RuntimeException("Not yet implemented!");
    }

    @Override
    public LifeSatisfactionEvaluation evaluate(LifeSatisfactionComputationVariablesDto variablesDto) {
        throw new RuntimeException("Not yet implemented!");
    }

    @Override
    public MeqEvaluation evaluate(MeqComputationVariablesDto variablesDto) {
        throw new RuntimeException("Not yet implemented!");
    }
}
