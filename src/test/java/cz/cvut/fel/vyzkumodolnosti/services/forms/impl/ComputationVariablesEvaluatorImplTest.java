package cz.cvut.fel.vyzkumodolnosti.services.forms.impl;

import cz.cvut.fel.vyzkumodolnosti.model.dto.forms.variables.LifeSatisfactionComputationVariablesDto;
import cz.cvut.fel.vyzkumodolnosti.model.entities.forms.evaluations.LifeSatisfactionEvaluation;
import cz.cvut.fel.vyzkumodolnosti.services.forms.api.ComputationVariablesEvaluator;
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
    void testEvaluate2() {
    }
}