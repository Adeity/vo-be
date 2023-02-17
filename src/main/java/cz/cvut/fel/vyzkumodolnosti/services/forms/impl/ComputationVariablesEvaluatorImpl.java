package cz.cvut.fel.vyzkumodolnosti.services.forms.impl;

import cz.cvut.fel.vyzkumodolnosti.model.dto.forms.variables.*;
import cz.cvut.fel.vyzkumodolnosti.model.entities.forms.evaluations.*;
import cz.cvut.fel.vyzkumodolnosti.services.forms.api.ComputationVariablesEvaluator;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class ComputationVariablesEvaluatorImpl implements ComputationVariablesEvaluator {
    @Override
    public PsqiEvaluation evaluate(PsqiComputationVariablesDto variablesDto) {
        PsqiComputator computator = new PsqiComputator();
        PsqiEvaluation evaluation = new PsqiEvaluation();

        evaluation.setAverageLaydownTime(variablesDto.getQ1());
        evaluation.setMinutesToFallAsleep(variablesDto.getQ2());
        evaluation.setAverageTimeOfGettingUp(variablesDto.getQ3());
        evaluation.setPsqidurat(computator.calculatePsqiDurat(variablesDto.getQ4()));
        evaluation.setPsqidistb(computator.calculatePsqidistb(
                variablesDto.getQ5bScore(),
                variablesDto.getQ5cScore(),
                variablesDto.getQ5dScore(),
                variablesDto.getQ5eScore(),
                variablesDto.getQ5fScore(),
                variablesDto.getQ5gScore(),
                variablesDto.getQ5hScore(),
                variablesDto.getQ5iScore(),
                variablesDto.getQ5jScore()));
        evaluation.setPsqilaten(computator.calculatePsqilaten(variablesDto.getQ2(), variablesDto.getQ5aScore()));
        evaluation.setPsqidaydys(computator.calculatePsqidaydys(variablesDto.getQ8Score(), variablesDto.getQ9Score()));
        evaluation.setPsqihse(computator.calculatePsqihse(
                variablesDto.getQ1(),
                variablesDto.getQ3(),
                variablesDto.getQ4()));
        evaluation.setPsqislpqual(variablesDto.getQ6Score());
        evaluation.setPsqimeds(variablesDto.getQ7Score());
        evaluation.setPsqitotal(
                evaluation.getPsqidurat() +
                        evaluation.getPsqidistb() +
                        evaluation.getPsqilaten() +
                        evaluation.getPsqidaydys() +
                        evaluation.getPsqihse() +
                        evaluation.getPsqislpqual() +
                        evaluation.getPsqimeds());
        evaluation.setSleeL

        return evaluation;
    }

    @Override
    public MctqEvaluation evaluate(MctqComputationVariablesDto variablesDto) {
        MctqEvaluation evaluation = new MctqEvaluation();
        MctqComputator computator = new MctqComputator();

        evaluation.setSOw(computator.calculateSOw(variablesDto.getSPrepw(), variablesDto.getSLatw()));
        evaluation.setGUw(computator.calculateGUw(variablesDto.getSEw(), variablesDto.getSIw()));
        evaluation.setSDw(computator.calculateSDw(variablesDto.getSEw(), evaluation.getSOw()));
        evaluation.setTBTw(computator.calculateTBTw(evaluation.getGUw(), variablesDto.getBTw()));
        evaluation.setMSW(computator.calculateMSW(evaluation.getSOw(), evaluation.getSDw()));

        evaluation.setSOf(computator.calculateSOf(variablesDto.getSPrepf(), variablesDto.getSLatf()));
        evaluation.setGUf(computator.calculateGUf(variablesDto.getSEf(), variablesDto.getSIf()));
        evaluation.setSDf(computator.calculateSDf(variablesDto.getSEf(), evaluation.getSOf()));
        evaluation.setTBTf(computator.calculateTBTf(evaluation.getGUf(), variablesDto.getBTf()));
        evaluation.setMSF(computator.calculateMSF(evaluation.getSOf(), evaluation.getSDf()));

        evaluation.setSDweek(computator.calculateSDweek(
                evaluation.getSDw(),
                variablesDto.getWD(),
                evaluation.getSDf(),
                variablesDto.getFD()));
        evaluation.setMSFsc(computator.calculateMSFsc(
                variablesDto.getAlarmf(),
                evaluation.getSDf(),
                evaluation.getSDw(),
                evaluation.getMSF(),
                evaluation.getSDweek()));
        evaluation.setSLossweek(computator.calculateSLossweek(
                evaluation.getSDweek(),
                evaluation.getSDw(),
                evaluation.getSDf(),
                variablesDto.getWD(),
                variablesDto.getFD()));
        evaluation.setSJLrel(computator.calculateSJLrel(evaluation.getMSF(), evaluation.getMSW()));
        evaluation.setSJL(computator.calculateSJL(evaluation.getMSF(), evaluation.getMSW()));
        evaluation.setLEweek(computator.calculateLEweek(
                variablesDto.getLEw(),
                variablesDto.getWD(),
                variablesDto.getFD(),
                variablesDto.getLEf()));

        return evaluation;
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

    @Override
    public MeqEvaluation evaluate(MeqComputationVariablesDto variablesDto) {
        MeqComputator computator = new MeqComputator();
        Integer q1 = computator.calculateQ1(variablesDto.getQ1());
        Integer q2 = computator.calculateQ2(variablesDto.getQ2());
        Integer q3 = variablesDto.getQ3();
        Integer q4 = variablesDto.getQ4();
        Integer q5 = variablesDto.getQ5();
        Integer q6 = variablesDto.getQ6();
        Integer q7 = variablesDto.getQ7();
        Integer q8 = variablesDto.getQ8();
        Integer q9 = variablesDto.getQ9();
        Integer q10 = computator.calculateQ10(variablesDto.getQ10());
        Integer q11 = variablesDto.getQ11();
        Integer q12 = variablesDto.getQ12();
        Integer q13 = variablesDto.getQ13();
        Integer q14 = variablesDto.getQ14();
        Integer q15 = variablesDto.getQ15();
        Integer q16 = variablesDto.getQ16();
        Integer q17 = computator.calculateQ17(variablesDto.getQ17());
        Integer q18 = computator.calculateQ18(variablesDto.getQ18());
        Integer q19 = variablesDto.getQ19();

        Integer sum = sumNInts(
                q1, q2, q3, q4, q5, q6, q7, q8, q9, q10, q11, q12, q13, q14, q15, q16, q17, q18, q19);

        MeqEvaluation evaluation = new MeqEvaluation();
        evaluation.setMeqValue(sum);
        return evaluation;
    }

    @Override
    public PssEvaluation evaluate(PssComputationVariablesDto variablesDto) {
        PssEvaluation evaluation = new PssEvaluation();

        return evaluation;
    }

    private Integer sumNInts(Integer... e) {
        return Arrays.stream(e).reduce(0, Integer::sum);
    }
}
