package cz.cvut.fel.vyzkumodolnosti.services.forms.impl;

import cz.cvut.fel.vyzkumodolnosti.model.dto.forms.variables.LifeSatisfactionComputationVariablesDto;
import cz.cvut.fel.vyzkumodolnosti.model.dto.forms.variables.PsqiComputationVariablesDto;
import cz.cvut.fel.vyzkumodolnosti.model.entities.forms.evaluations.LifeSatisfactionEvaluation;
import cz.cvut.fel.vyzkumodolnosti.model.entities.forms.evaluations.PsqiEvaluation;
import cz.cvut.fel.vyzkumodolnosti.services.forms.api.ComputationVariablesEvaluator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


class ComputationVariablesEvaluatorImplTest {
    ComputationVariablesEvaluator evaluator = new ComputationVariablesEvaluatorImpl();

    @Test
    void evaluate() {
    }

    @Test
    void testEvaluate() {
    }

    @Test
    void test_lifesat_1() {
        LifeSatisfactionComputationVariablesDto dto = new LifeSatisfactionComputationVariablesDto();
        dto.setZDR(List.of(
                1, 3, 5, 3, 2, 3, 7));
        dto.setPRZ(List.of(
                1, 7, 4, 5, 3, 2, 6));
        dto.setFIN(List.of(
                7, 2, 4, 1, 5, 3, 6));
        dto.setVOL(List.of(
                4, 5, 6, 1, 3, 7, 2));
        dto.setMAN(List.of(
                4, 1, 2, 3, 7, 5, 6));
        dto.setDET(List.of(
                6, 5, 3, 1, 4, 7, 2));
        dto.setVLO(List.of(
                7, 3, 2, 1, 4, 6, 5));
        dto.setSEX(List.of(
                4, 7, 3, 6, 5, 2, 1));
        dto.setPZP(List.of(
                1, 2, 4, 3, 5, 1, 6));
        dto.setBYD(List.of(
                5, 6, 7, 1, 2, 3, 4));

        LifeSatisfactionEvaluation evaluation = evaluator.evaluate(dto);

        assertEquals(24, evaluation.getZDR());
        assertEquals(28, evaluation.getPRZ());
        assertEquals(28, evaluation.getFIN());
        assertEquals(28, evaluation.getVOL());
        assertEquals(28, evaluation.getMAN());
        assertEquals(28, evaluation.getDET());
        assertEquals(28, evaluation.getVLO());
        assertEquals(28, evaluation.getSEX());
        assertEquals(22, evaluation.getPZP());
        assertEquals(28, evaluation.getBYD());

        assertEquals(270, evaluation.getSUM());

    }

    @Test
    void test_psqi_1() {
        PsqiComputationVariablesDto computationVariablesDto = new PsqiComputationVariablesDto();
        computationVariablesDto.setQ1("22:15");
        computationVariablesDto.setQ2(10);
        computationVariablesDto.setQ3("6:30");
        computationVariablesDto.setQ4(7.00);
        computationVariablesDto.setQ5aScore(1);
        computationVariablesDto.setQ5bScore(2);
        computationVariablesDto.setQ5cScore(0);
        computationVariablesDto.setQ5dScore(0);
        computationVariablesDto.setQ5eScore(0);
        computationVariablesDto.setQ5fScore(1);
        computationVariablesDto.setQ5gScore(1);
        computationVariablesDto.setQ5hScore(1);
        computationVariablesDto.setQ5iScore(0);
        computationVariablesDto.setQ5jScore(2);
        computationVariablesDto.setQ6Score(1);
        computationVariablesDto.setQ7Score(0);
        computationVariablesDto.setQ8Score(1);
        computationVariablesDto.setQ9Score(0);

        PsqiEvaluation evaluationExpected = new PsqiEvaluation();

        evaluationExpected.setAverageLaydownTime("22:15");
        evaluationExpected.setMinutesToFallAsleep(10);
        evaluationExpected.setAverageTimeOfGettingUp("6:30");
        evaluationExpected.setPsqidurat(0);
        evaluationExpected.setPsqidistb(1);
        evaluationExpected.setPsqilaten(1);
        evaluationExpected.setPsqidaydys(1);
        evaluationExpected.setPsqihse(1);
        evaluationExpected.setPsqislpqual(1);
        evaluationExpected.setPsqimeds(0);
        evaluationExpected.setPsqitotal(5);

        PsqiEvaluation evaluationActual = evaluator.evaluate(computationVariablesDto);

        assertEquals(evaluationExpected, evaluationActual);
    }

    @Test
    void test_psqi_2() {
        PsqiComputationVariablesDto computationVariablesDto = new PsqiComputationVariablesDto();
        computationVariablesDto.setQ1("22:30");
        computationVariablesDto.setQ2(10);
        computationVariablesDto.setQ3("6:30");
        computationVariablesDto.setQ4(5.50);
        computationVariablesDto.setQ5aScore(0);
        computationVariablesDto.setQ5bScore(3);
        computationVariablesDto.setQ5cScore(0);
        computationVariablesDto.setQ5dScore(0);
        computationVariablesDto.setQ5eScore(0);
        computationVariablesDto.setQ5fScore(0);
        computationVariablesDto.setQ5gScore(0);
        computationVariablesDto.setQ5hScore(1);
        computationVariablesDto.setQ5iScore(0);
        computationVariablesDto.setQ5jScore(3);
        computationVariablesDto.setQ6Score(2);
        computationVariablesDto.setQ7Score(0);
        computationVariablesDto.setQ8Score(2);
        computationVariablesDto.setQ9Score(1);

        PsqiEvaluation evaluationExpected = new PsqiEvaluation();

        evaluationExpected.setAverageLaydownTime("22:30");
        evaluationExpected.setMinutesToFallAsleep(10);
        evaluationExpected.setAverageTimeOfGettingUp("6:30");
        evaluationExpected.setPsqidurat(2);
        evaluationExpected.setPsqidistb(1);
        evaluationExpected.setPsqilaten(0);
        evaluationExpected.setPsqidaydys(2);
        evaluationExpected.setPsqihse(2);
        evaluationExpected.setPsqislpqual(2);
        evaluationExpected.setPsqimeds(0);
        evaluationExpected.setPsqitotal(9);

        PsqiEvaluation evaluationActual = evaluator.evaluate(computationVariablesDto);

        assertEquals(evaluationExpected, evaluationActual);
    }
}