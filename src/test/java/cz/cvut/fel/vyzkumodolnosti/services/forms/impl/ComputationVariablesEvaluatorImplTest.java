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
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


class ComputationVariablesEvaluatorImplTest {
    ComputationVariablesEvaluator evaluator = new ComputationVariablesEvaluatorImpl();

    @Test
    void evaluate() {
    }

    @Test
    void testEvaluate() {
    }



    @Test
    void test_psqi_1() {
        PsqiComputationVariablesDto computationVariablesDto = new PsqiComputationVariablesDto();
        computationVariablesDto.setQ1("22:15");
        computationVariablesDto.setQ2(10);
        computationVariablesDto.setQ3("6:30");
        computationVariablesDto.setQ4(7.00);
        computationVariablesDto.setQ5a(1);
        computationVariablesDto.setQ5b(2);
        computationVariablesDto.setQ5c(0);
        computationVariablesDto.setQ5d(0);
        computationVariablesDto.setQ5e(0);
        computationVariablesDto.setQ5f(1);
        computationVariablesDto.setQ5g(1);
        computationVariablesDto.setQ5h(1);
        computationVariablesDto.setQ5i(0);
        computationVariablesDto.setQ5j(2);
        computationVariablesDto.setQ6(1);
        computationVariablesDto.setQ7(0);
        computationVariablesDto.setQ8(1);
        computationVariablesDto.setQ9(0);
        computationVariablesDto.setWorkDaysGnt("21:45");
        computationVariablesDto.setWorkDaysGmt("04:45");
        computationVariablesDto.setFreeDaysGnt("23:00");
        computationVariablesDto.setFreeDaysGmt("08:10");

        PsqiEvaluation evaluationExpected = new PsqiEvaluation();

        evaluationExpected.setAverageLaydownTime("22:15");
        evaluationExpected.setMinutesToFallAsleep(10);
        evaluationExpected.setAverageTimeOfGettingUp("06:30");
        evaluationExpected.setPsqidurat(0);
        evaluationExpected.setPsqidistb(1);
        evaluationExpected.setPsqilaten(1);
        evaluationExpected.setPsqidaydys(1);
        evaluationExpected.setPsqihse(1);
        evaluationExpected.setPsqislpqual(1);
        evaluationExpected.setPsqimeds(0);
        evaluationExpected.setPsqitotal(5);
        evaluationExpected.setMidSleepWorkDays("01:15");
        evaluationExpected.setMidSleepFreeDays("03:35");
        evaluationExpected.setSleepDurationWorkDays("07:00");
        evaluationExpected.setSleepDurationFreeDays("09:10");
        evaluationExpected.setSJL("02:15");

        PsqiEvaluation evaluationActual = evaluator.evaluate(computationVariablesDto);

        Assertions.assertEquals(evaluationExpected.getAverageLaydownTime(), evaluationActual.getAverageLaydownTime());
        Assertions.assertEquals(evaluationExpected.getMinutesToFallAsleep(), evaluationActual.getMinutesToFallAsleep());
        Assertions.assertEquals(evaluationExpected.getAverageTimeOfGettingUp(), evaluationActual.getAverageTimeOfGettingUp());
        Assertions.assertEquals(evaluationExpected.getPsqidurat(), evaluationActual.getPsqidurat());
        Assertions.assertEquals(evaluationExpected.getPsqidistb(), evaluationActual.getPsqidistb());
        Assertions.assertEquals(evaluationExpected.getPsqilaten(), evaluationActual.getPsqilaten());
        Assertions.assertEquals(evaluationExpected.getPsqidaydys(), evaluationActual.getPsqidaydys());
        Assertions.assertEquals(evaluationExpected.getPsqihse(), evaluationActual.getPsqihse());
        Assertions.assertEquals(evaluationExpected.getPsqislpqual(), evaluationActual.getPsqislpqual());
        Assertions.assertEquals(evaluationExpected.getPsqimeds(), evaluationActual.getPsqimeds());
        Assertions.assertEquals(evaluationExpected.getPsqitotal(), evaluationActual.getPsqitotal());
        Assertions.assertEquals(evaluationExpected.getMidSleepFreeDays(), evaluationActual.getMidSleepFreeDays());
        Assertions.assertEquals(evaluationExpected.getMidSleepWorkDays(), evaluationActual.getMidSleepWorkDays());
        Assertions.assertEquals(evaluationExpected.getSleepDurationFreeDays(), evaluationActual.getSleepDurationFreeDays());
        Assertions.assertEquals(evaluationExpected.getSleepDurationWorkDays(), evaluationActual.getSleepDurationWorkDays());
    }

    @Test
    void test_psqi_2() {
        PsqiComputationVariablesDto computationVariablesDto = new PsqiComputationVariablesDto();
        computationVariablesDto.setQ1("22:30");
        computationVariablesDto.setQ2(10);
        computationVariablesDto.setQ3("6:30");
        computationVariablesDto.setQ4(5.50);
        computationVariablesDto.setQ5a(0);
        computationVariablesDto.setQ5b(3);
        computationVariablesDto.setQ5c(0);
        computationVariablesDto.setQ5d(0);
        computationVariablesDto.setQ5e(0);
        computationVariablesDto.setQ5f(0);
        computationVariablesDto.setQ5g(0);
        computationVariablesDto.setQ5h(1);
        computationVariablesDto.setQ5i(0);
        computationVariablesDto.setQ5j(3);
        computationVariablesDto.setQ6(2);
        computationVariablesDto.setQ7(0);
        computationVariablesDto.setQ8(2);
        computationVariablesDto.setQ9(1);
        computationVariablesDto.setWorkDaysGnt("21:45");
        computationVariablesDto.setWorkDaysGmt("04:45");
        computationVariablesDto.setFreeDaysGnt("23:00");
        computationVariablesDto.setFreeDaysGmt("08:10");

        PsqiEvaluation evaluationExpected = new PsqiEvaluation();

        evaluationExpected.setAverageLaydownTime("22:30");
        evaluationExpected.setMinutesToFallAsleep(10);
        evaluationExpected.setAverageTimeOfGettingUp("06:30");
        evaluationExpected.setPsqidurat(2);
        evaluationExpected.setPsqidistb(1);
        evaluationExpected.setPsqilaten(0);
        evaluationExpected.setPsqidaydys(2);
        evaluationExpected.setPsqihse(2);
        evaluationExpected.setPsqislpqual(2);
        evaluationExpected.setPsqimeds(0);
        evaluationExpected.setPsqitotal(9);
        evaluationExpected.setMidSleepWorkDays("01:15");
        evaluationExpected.setMidSleepFreeDays("03:35");
        evaluationExpected.setSleepDurationWorkDays("07:00");
        evaluationExpected.setSleepDurationFreeDays("09:10");
        evaluationExpected.setSJL("02:15");

        PsqiEvaluation evaluationActual = evaluator.evaluate(computationVariablesDto);

        Assertions.assertEquals(evaluationExpected.getAverageLaydownTime(), evaluationActual.getAverageLaydownTime());
        Assertions.assertEquals(evaluationExpected.getMinutesToFallAsleep(), evaluationActual.getMinutesToFallAsleep());
        Assertions.assertEquals(evaluationExpected.getAverageTimeOfGettingUp(), evaluationActual.getAverageTimeOfGettingUp());
        Assertions.assertEquals(evaluationExpected.getPsqidurat(), evaluationActual.getPsqidurat());
        Assertions.assertEquals(evaluationExpected.getPsqidistb(), evaluationActual.getPsqidistb());
        Assertions.assertEquals(evaluationExpected.getPsqilaten(), evaluationActual.getPsqilaten());
        Assertions.assertEquals(evaluationExpected.getPsqidaydys(), evaluationActual.getPsqidaydys());
        Assertions.assertEquals(evaluationExpected.getPsqihse(), evaluationActual.getPsqihse());
        Assertions.assertEquals(evaluationExpected.getPsqislpqual(), evaluationActual.getPsqislpqual());
        Assertions.assertEquals(evaluationExpected.getPsqimeds(), evaluationActual.getPsqimeds());
        Assertions.assertEquals(evaluationExpected.getPsqitotal(), evaluationActual.getPsqitotal());
        Assertions.assertEquals(evaluationExpected.getMidSleepFreeDays(), evaluationActual.getMidSleepFreeDays());
        Assertions.assertEquals(evaluationExpected.getMidSleepWorkDays(), evaluationActual.getMidSleepWorkDays());
        Assertions.assertEquals(evaluationExpected.getSleepDurationFreeDays(), evaluationActual.getSleepDurationFreeDays());
        Assertions.assertEquals(evaluationExpected.getSleepDurationWorkDays(), evaluationActual.getSleepDurationWorkDays());

//        assertTrue(evaluationExpected.equals(evaluationActual));
    }

    @Test
    void test_mctq_1() {
        MctqComputationVariablesDto computationVariablesDto = new MctqComputationVariablesDto();
        computationVariablesDto.setBtw("21:15");
        computationVariablesDto.setSprepw("21:30");
        computationVariablesDto.setSlatw(15);
        computationVariablesDto.setSew("4:45");
        computationVariablesDto.setAlarmw(true);
        computationVariablesDto.setSiw(0);
        computationVariablesDto.setWd(5);
        computationVariablesDto.setLew("2:30");
        computationVariablesDto.setBtf("22:00");
        computationVariablesDto.setSprepf("22:30");
        computationVariablesDto.setSlatf(30);
        computationVariablesDto.setSef("08:00");
        computationVariablesDto.setAlarmf(false);
        computationVariablesDto.setSif(10);
        computationVariablesDto.setFd(2);
        computationVariablesDto.setLef("6:00");

        MctqEvaluation evaluationExpected = new MctqEvaluation();
        evaluationExpected.setSOw("21:45");
        evaluationExpected.setGUw("04:45");
        evaluationExpected.setSDw("07:00");
        evaluationExpected.setTBTw("07:30");
        evaluationExpected.setMSW("01:15");
        evaluationExpected.setSOf("23:00");
        evaluationExpected.setGUf("08:10");
        evaluationExpected.setSDf("09:00");
        evaluationExpected.setTBTf("10:10");
        evaluationExpected.setMSF("03:30");
        evaluationExpected.setSDweek("07:34");
        evaluationExpected.setMSFsc("02:47");
        evaluationExpected.setSLossweek("02:50");
        evaluationExpected.setSJLrel("02:15");
        evaluationExpected.setSJL("02:15");
        evaluationExpected.setLEweek("03:30");

        MctqEvaluation evaluationActual = evaluator.evaluate(computationVariablesDto);

        Assertions.assertEquals(evaluationExpected.getSOw(), evaluationActual.getSOw());
        Assertions.assertEquals(evaluationExpected.getGUw(), evaluationActual.getGUw());
        Assertions.assertEquals(evaluationExpected.getTBTw(), evaluationActual.getTBTw());
        Assertions.assertEquals(evaluationExpected.getMSW(), evaluationActual.getMSW());
        Assertions.assertEquals(evaluationExpected.getSOf(), evaluationActual.getSOf());
        Assertions.assertEquals(evaluationExpected.getGUf(), evaluationActual.getGUf());
        Assertions.assertEquals(evaluationExpected.getTBTf(), evaluationActual.getTBTf());
        Assertions.assertEquals(evaluationExpected.getMSF(), evaluationActual.getMSF());
        Assertions.assertEquals(evaluationExpected.getSDweek(), evaluationActual.getSDweek());
        Assertions.assertEquals(evaluationExpected.getMSFsc(), evaluationActual.getMSFsc());
        Assertions.assertEquals(evaluationExpected.getSLossweek(), evaluationActual.getSLossweek());
        Assertions.assertEquals(evaluationExpected.getSJLrel(), evaluationActual.getSJLrel());
        Assertions.assertEquals(evaluationExpected.getSJL(), evaluationActual.getSJL());
        Assertions.assertEquals(evaluationExpected.getLEweek(), evaluationActual.getLEweek());
    }

    @Test
    void test_mctq_2() {
        MctqComputationVariablesDto computationVariablesDto = new MctqComputationVariablesDto();
        computationVariablesDto.setBtw("22:40");
        computationVariablesDto.setSprepw("22:50");
        computationVariablesDto.setSlatw(10);
        computationVariablesDto.setSew("7:00");
        computationVariablesDto.setAlarmw(true);
        computationVariablesDto.setSiw(0);
        computationVariablesDto.setWd(5);
        computationVariablesDto.setLew("2:00");
        computationVariablesDto.setBtf("22:40");
        computationVariablesDto.setSprepf("22:50");
        computationVariablesDto.setSlatf(10);
        computationVariablesDto.setSef("08:30");
        computationVariablesDto.setAlarmf(false);
        computationVariablesDto.setSif(10);
        computationVariablesDto.setFd(2);
        computationVariablesDto.setLef("4:00");

        MctqEvaluation evaluationExpected = new MctqEvaluation();
        evaluationExpected.setSOw("23:00");
        evaluationExpected.setGUw("07:00");
        evaluationExpected.setSDw("08:00");
        evaluationExpected.setTBTw("08:20");
        evaluationExpected.setMSW("03:00");
        evaluationExpected.setSOf("23:00");
        evaluationExpected.setGUf("08:40");
        evaluationExpected.setSDf("09:30");
        evaluationExpected.setTBTf("10:00");
        evaluationExpected.setMSF("03:45");
        evaluationExpected.setSDweek("08:25");
        evaluationExpected.setMSFsc("03:12");
        evaluationExpected.setSLossweek("02:05");
        evaluationExpected.setSJLrel("00:45");
        evaluationExpected.setSJL("00:45");
        evaluationExpected.setLEweek("02:34");

        MctqEvaluation evaluationActual = evaluator.evaluate(computationVariablesDto);

        Assertions.assertEquals(evaluationExpected.getSOw(), evaluationActual.getSOw());
        Assertions.assertEquals(evaluationExpected.getGUw(), evaluationActual.getGUw());
        Assertions.assertEquals(evaluationExpected.getTBTw(), evaluationActual.getTBTw());
        Assertions.assertEquals(evaluationExpected.getMSW(), evaluationActual.getMSW());
        Assertions.assertEquals(evaluationExpected.getSOf(), evaluationActual.getSOf());
        Assertions.assertEquals(evaluationExpected.getGUf(), evaluationActual.getGUf());
        Assertions.assertEquals(evaluationExpected.getTBTf(), evaluationActual.getTBTf());
        Assertions.assertEquals(evaluationExpected.getMSF(), evaluationActual.getMSF());
        Assertions.assertEquals(evaluationExpected.getSDweek(), evaluationActual.getSDweek());
        Assertions.assertEquals(evaluationExpected.getMSFsc(), evaluationActual.getMSFsc());
        Assertions.assertEquals(evaluationExpected.getSLossweek(), evaluationActual.getSLossweek());
        Assertions.assertEquals(evaluationExpected.getSJLrel(), evaluationActual.getSJLrel());
        Assertions.assertEquals(evaluationExpected.getSJL(), evaluationActual.getSJL());
        Assertions.assertEquals(evaluationExpected.getLEweek(), evaluationActual.getLEweek());
    }

    @Test
    void test_mctq_3() {
        MctqComputationVariablesDto computationVariablesDto = new MctqComputationVariablesDto();
        computationVariablesDto.setBtw("22:40");
        computationVariablesDto.setSprepw("22:50");
        computationVariablesDto.setSlatw(10);
        computationVariablesDto.setSew("7:00");
        computationVariablesDto.setAlarmw(true);
        computationVariablesDto.setSiw(0);
        computationVariablesDto.setWd(5);
        computationVariablesDto.setLew("2:00");
        computationVariablesDto.setBtf("22:40");
        computationVariablesDto.setSprepf("22:50");
        computationVariablesDto.setSlatf(10);
        computationVariablesDto.setSef("08:30");
        computationVariablesDto.setAlarmf(true);
        computationVariablesDto.setSif(10);
        computationVariablesDto.setFd(2);
        computationVariablesDto.setLef("4:00");

        MctqEvaluation evaluationExpected = new MctqEvaluation();
        evaluationExpected.setSOw("23:00");
        evaluationExpected.setGUw("07:00");
        evaluationExpected.setSDw("08:00");
        evaluationExpected.setTBTw("08:20");
        evaluationExpected.setMSW("03:00");
        evaluationExpected.setSOf("23:00");
        evaluationExpected.setGUf("08:40");
        evaluationExpected.setSDf("09:30");
        evaluationExpected.setTBTf("10:00");
        evaluationExpected.setMSF("03:45");
        evaluationExpected.setSDweek("08:25");
        evaluationExpected.setMSFsc("");
        evaluationExpected.setSLossweek("02:05");
        evaluationExpected.setSJLrel("00:45");
        evaluationExpected.setSJL("00:45");
        evaluationExpected.setLEweek("02:34");

        MctqEvaluation evaluationActual = evaluator.evaluate(computationVariablesDto);

        Assertions.assertEquals(evaluationExpected.getSOw(), evaluationActual.getSOw());
        Assertions.assertEquals(evaluationExpected.getGUw(), evaluationActual.getGUw());
        Assertions.assertEquals(evaluationExpected.getTBTw(), evaluationActual.getTBTw());
        Assertions.assertEquals(evaluationExpected.getMSW(), evaluationActual.getMSW());
        Assertions.assertEquals(evaluationExpected.getSOf(), evaluationActual.getSOf());
        Assertions.assertEquals(evaluationExpected.getGUf(), evaluationActual.getGUf());
        Assertions.assertEquals(evaluationExpected.getTBTf(), evaluationActual.getTBTf());
        Assertions.assertEquals(evaluationExpected.getMSF(), evaluationActual.getMSF());
        Assertions.assertEquals(evaluationExpected.getSDweek(), evaluationActual.getSDweek());
        Assertions.assertEquals(evaluationExpected.getMSFsc(), evaluationActual.getMSFsc());
        Assertions.assertEquals(evaluationExpected.getSLossweek(), evaluationActual.getSLossweek());
        Assertions.assertEquals(evaluationExpected.getSJLrel(), evaluationActual.getSJLrel());
        Assertions.assertEquals(evaluationExpected.getSJL(), evaluationActual.getSJL());
        Assertions.assertEquals(evaluationExpected.getLEweek(), evaluationActual.getLEweek());
    }

    @Test
    void test_meq_1() {
        MeqComputationVariablesDto computationVariablesDto = new MeqComputationVariablesDto();
        computationVariablesDto.setQ1("06:00"); // 5
        computationVariablesDto.setQ2("22:00"); // 4
        computationVariablesDto.setQ3(2);
        computationVariablesDto.setQ4(4);
        computationVariablesDto.setQ5(3);
        computationVariablesDto.setQ6(2);
        computationVariablesDto.setQ7(3);
        computationVariablesDto.setQ8(4);
        computationVariablesDto.setQ9(4);
        computationVariablesDto.setQ10("21:30"); // 4
        computationVariablesDto.setQ11(6);
        computationVariablesDto.setQ12(3);
        computationVariablesDto.setQ13(1);
        computationVariablesDto.setQ14(3);
        computationVariablesDto.setQ15(1);
        computationVariablesDto.setQ16(1);
        computationVariablesDto.setQ17("14:00"); // 2
        computationVariablesDto.setQ18("19:00"); // 2
        computationVariablesDto.setQ19(6);

        MeqEvaluation meqEvaluationExpected = new MeqEvaluation();
        meqEvaluationExpected.setMeqValue(60);

        MeqEvaluation evaluationActual = evaluator.evaluate(computationVariablesDto);

        Assertions.assertEquals(meqEvaluationExpected.getMeqValue(), evaluationActual.getMeqValue());

    }
}