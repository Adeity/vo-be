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

import java.util.Arrays;

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
        LifeSatisfactionEvaluation evaluation = new LifeSatisfactionEvaluation();

        evaluation.setZDR(variablesDto.getZDR().stream().reduce(0, Integer::sum));
        evaluation.setPRZ(variablesDto.getPRZ().stream().reduce(0, Integer::sum));
        evaluation.setFIN(variablesDto.getFIN().stream().reduce(0, Integer::sum));
        evaluation.setVOL(variablesDto.getVOL().stream().reduce(0, Integer::sum));
        evaluation.setMAN(variablesDto.getMAN().stream().reduce(0, Integer::sum));
        evaluation.setDET(variablesDto.getDET().stream().reduce(0, Integer::sum));
        evaluation.setVLO(variablesDto.getVLO().stream().reduce(0, Integer::sum));
        evaluation.setSEX(variablesDto.getSEX().stream().reduce(0, Integer::sum));
        evaluation.setPZP(variablesDto.getPZP().stream().reduce(0, Integer::sum));
        evaluation.setBYD(variablesDto.getBYD().stream().reduce(0, Integer::sum));

        evaluation.setSUM(
                sumNInts(
                        evaluation.getZDR(),
                        evaluation.getPRZ(),
                        evaluation.getFIN(),
                        evaluation.getVOL(),
                        evaluation.getMAN(),
                        evaluation.getDET(),
                        evaluation.getVLO(),
                        evaluation.getSEX(),
                        evaluation.getPZP(),
                        evaluation.getBYD()));

        return evaluation;
    }

    private Integer sumNInts(Integer... e) {
        return Arrays.stream(e).reduce(0, Integer::sum);
    }

    @Override
    public MeqEvaluation evaluate(MeqComputationVariablesDto variablesDto) {
        throw new RuntimeException("Not yet implemented!");
    }
}
