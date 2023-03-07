package cz.cvut.fel.vyzkumodolnosti.services.forms.impl;

import cz.cvut.fel.vyzkumodolnosti.model.dto.forms.variables.*;
import cz.cvut.fel.vyzkumodolnosti.model.entities.forms.evaluations.*;
import cz.cvut.fel.vyzkumodolnosti.services.TimeComponent;
import cz.cvut.fel.vyzkumodolnosti.services.forms.api.ComputationVariablesEvaluator;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class ComputationVariablesEvaluatorImpl implements ComputationVariablesEvaluator {
    @Override
    public PsqiEvaluation evaluate(PsqiComputationVariablesDto variablesDto) {
        PsqiComputator computator = new PsqiComputator();
        PsqiEvaluation evaluation = new PsqiEvaluation();
        TimeComponent timeComponent = new TimeComponent();

        evaluation.setAverageLaydownTime(timeComponent.addTralingZeroIfNeeded(variablesDto.getQ1()));
        evaluation.setMinutesToFallAsleep(variablesDto.getQ2());
        evaluation.setAverageTimeOfGettingUp(timeComponent.addTralingZeroIfNeeded(variablesDto.getQ3()));
        evaluation.setPsqidurat(computator.calculatePsqiDurat(variablesDto.getQ4()));
        evaluation.setPsqidistb(computator.calculatePsqidistb(
                variablesDto.getQ5b(),
                variablesDto.getQ5c(),
                variablesDto.getQ5d(),
                variablesDto.getQ5e(),
                variablesDto.getQ5f(),
                variablesDto.getQ5g(),
                variablesDto.getQ5h(),
                variablesDto.getQ5i(),
                variablesDto.getQ5j()));
        evaluation.setPsqilaten(computator.calculatePsqilaten(variablesDto.getQ2(), variablesDto.getQ5a()));
        evaluation.setPsqidaydys(computator.calculatePsqidaydys(variablesDto.getQ8(), variablesDto.getQ9()));
        evaluation.setPsqihse(computator.calculatePsqihse(
                variablesDto.getQ1(),
                variablesDto.getQ3(),
                variablesDto.getQ4()));
        evaluation.setPsqislpqual(variablesDto.getQ6());
        evaluation.setPsqimeds(variablesDto.getQ7());
        evaluation.setPsqitotal(
                evaluation.getPsqidurat() +
                        evaluation.getPsqidistb() +
                        evaluation.getPsqilaten() +
                        evaluation.getPsqidaydys() +
                        evaluation.getPsqihse() +
                        evaluation.getPsqislpqual() +
                        evaluation.getPsqimeds());
        evaluation.setSleepDurationFreeDays(computator.calculateSleepDurationFreeDays(
                variablesDto.getFreeDaysGnt(), variablesDto.getFreeDaysGmt()));
        evaluation.setSleepDurationWorkDays(computator.calculateSleepDurationWorkDays(
                variablesDto.getWorkDaysGnt(), variablesDto.getWorkDaysGmt()));
        evaluation.setMidSleepFreeDays(computator.calculateMidSleepFreeDays(
                variablesDto.getFreeDaysGnt(), evaluation.getSleepDurationFreeDays()));
        evaluation.setMidSleepWorkDays(computator.calculateMidSleepWorkDays(
                variablesDto.getWorkDaysGnt(), evaluation.getSleepDurationWorkDays()));
        evaluation.setSJL(computator.calculateSJL(evaluation.getMidSleepFreeDays(), evaluation.getMidSleepWorkDays()));

        return evaluation;
    }

    @Override
    public MctqEvaluation evaluate(MctqComputationVariablesDto variablesDto) {
        MctqEvaluation evaluation = new MctqEvaluation();
        MctqComputator computator = new MctqComputator();
        variablesDto.setFd(7 - variablesDto.getWd());

        evaluation.setSOw(computator.calculateSOw(variablesDto.getSprepw(), variablesDto.getSlatw()));
        evaluation.setGUw(computator.calculateGUw(variablesDto.getSew(), variablesDto.getSiw()));
        evaluation.setSDw(computator.calculateSDw(variablesDto.getSew(), evaluation.getSOw()));
        evaluation.setTBTw(computator.calculateTBTw(evaluation.getGUw(), variablesDto.getBtw()));
        evaluation.setMSW(computator.calculateMSW(evaluation.getSOw(), evaluation.getSDw()));

        evaluation.setSOf(computator.calculateSOf(variablesDto.getSprepf(), variablesDto.getSlatf()));
        evaluation.setGUf(computator.calculateGUf(variablesDto.getSef(), variablesDto.getSif()));
        evaluation.setSDf(computator.calculateSDf(variablesDto.getSef(), evaluation.getSOf()));
        evaluation.setTBTf(computator.calculateTBTf(evaluation.getGUf(), variablesDto.getBtf()));
        evaluation.setMSF(computator.calculateMSF(evaluation.getSOf(), evaluation.getSDf()));

        evaluation.setSDweek(computator.calculateSDweek(
                evaluation.getSDw(),
                variablesDto.getWd(),
                evaluation.getSDf(),
                variablesDto.getFd()));
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
                variablesDto.getWd(),
                variablesDto.getFd()));
        evaluation.setSJLrel(computator.calculateSJLrel(evaluation.getMSF(), evaluation.getMSW()));
        evaluation.setSJL(computator.calculateSJL(evaluation.getMSF(), evaluation.getMSW()));
        evaluation.setLEweek(computator.calculateLEweek(
                variablesDto.getLew(),
                variablesDto.getWd(),
                variablesDto.getFd(),
                variablesDto.getLef()));

        return evaluation;
    }

    @Override
    public LifeSatisfactionEvaluation evaluate(LifeSatisfactionComputationVariablesDto v) {
        LifeSatisfactionEvaluation evaluation = new LifeSatisfactionEvaluation();

        evaluation.setZDR(
                v.getHealthQ0() +v.getHealthQ1() +v.getHealthQ2() +v.getHealthQ3() +v.getHealthQ4() +v.getHealthQ5() +v.getHealthQ6()
        );
        evaluation.setPRZ(
                v.getWorkQ0() +v.getWorkQ1() +v.getWorkQ2() +v.getWorkQ3() +v.getWorkQ4() +v.getWorkQ5() +v.getWorkQ6()
        );
        evaluation.setFIN(
                v.getFinancesQ0() +v.getFinancesQ1() +v.getFinancesQ2() +v.getFinancesQ3() +v.getFinancesQ4() +v.getFinancesQ5() +v.getFinancesQ6()
        );
        evaluation.setVOL(
                v.getFreeTimeQ0() +v.getFreeTimeQ1() +v.getFreeTimeQ2() +v.getFreeTimeQ3() +v.getFreeTimeQ4() +v.getFreeTimeQ5() +v.getFreeTimeQ6()
        );
        if (v.getHasPartner()) {
            evaluation.setMAN(
                    v.getPartnershipQ0() +v.getPartnershipQ1() +v.getPartnershipQ2() +v.getPartnershipQ3() +v.getPartnershipQ4() +v.getPartnershipQ5() +v.getPartnershipQ6()
            );
        } else {
            evaluation.setMAN(0);
        }
        if (v.getHasKids()) {
            evaluation.setDET(
                    v.getChildrenQ0() +v.getChildrenQ1() +v.getChildrenQ2() +v.getChildrenQ3() +v.getChildrenQ4() +v.getChildrenQ5() +v.getChildrenQ6()
            );
        } else {
            evaluation.setDET(0);
        }
        evaluation.setVLO(
                v.getPersonalityQ0() +v.getPersonalityQ1() +v.getPersonalityQ2() +v.getPersonalityQ3() +v.getPersonalityQ4() +v.getPersonalityQ5() +v.getPersonalityQ6()
        );
        evaluation.setSEX(
                v.getSexualityQ0() +v.getSexualityQ1() +v.getSexualityQ2() +v.getSexualityQ3() +v.getSexualityQ4() +v.getSexualityQ5() +v.getSexualityQ6()
        );
        evaluation.setPZP(
                v.getFriendsQ0() +v.getFriendsQ1() +v.getFriendsQ2() +v.getFriendsQ3() +v.getFriendsQ4() +v.getFriendsQ5() +v.getFriendsQ6()
        );
        evaluation.setBYD(
                v.getHabitationQ0() +v.getHabitationQ1() +v.getHabitationQ2() +v.getHabitationQ3() +v.getHabitationQ4() +v.getHabitationQ5() +v.getHabitationQ6()
        );

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

        evaluation.setHasKids(v.getHasKids());
        evaluation.setHasPartner(v.getHasPartner());

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
    public PssEvaluation evaluate(PssComputationVariablesDto v) {
        PssEvaluation evaluation = new PssEvaluation();
        evaluation.setPssPos(v.getQ1() + v.getQ2() + v.getQ3() + v.getQ6() + v.getQ9() + v.getQ10());
        evaluation.setPssNeg(v.getQ4() + v.getQ5() + v.getQ7() + v.getQ8());
        evaluation.setPssSum(evaluation.getPssPos() + evaluation.getPssNeg());

        return evaluation;
    }

    private Integer sumNInts(Integer... e) {
        return Arrays.stream(e).reduce(0, Integer::sum);
    }
}
