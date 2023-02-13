package cz.cvut.fel.vyzkumodolnosti.services.computations;

import cz.cvut.fel.vyzkumodolnosti.model.domain.computations.ChronoVsRythm;
import cz.cvut.fel.vyzkumodolnosti.model.domain.computations.ChronotypeEnum;
import cz.cvut.fel.vyzkumodolnosti.model.entities.computations.GlobalChronotypeValue;
import cz.cvut.fel.vyzkumodolnosti.model.entities.computations.SleepComputationForm;
import cz.cvut.fel.vyzkumodolnosti.model.entities.computations.UserComputationData;
import cz.cvut.fel.vyzkumodolnosti.model.entities.forms.evaluations.MctqEvaluation;
import cz.cvut.fel.vyzkumodolnosti.model.entities.forms.evaluations.MeqEvaluation;
import cz.cvut.fel.vyzkumodolnosti.model.entities.forms.evaluations.PsqiEvaluation;
import cz.cvut.fel.vyzkumodolnosti.repository.computations.SleepComputationFormRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

@Service
public class SleepComputationFormsService {

    @Autowired
    private GlobalChronotypeValuesService chronoService;
    @Autowired
    private UserComputationDataService userService;

    @Autowired
    private FormsEvalService formsEvalService;

    @Autowired
    private SleepComputationFormRepository repository;


    public SleepComputationForm getComputationForm(long id) {
        return this.repository.findById(id);
    }

    public SleepComputationForm computeOverFormsData(MctqEvaluation mctq, MeqEvaluation meq, PsqiEvaluation psqi, String userId) throws Exception {

        List<GlobalChronotypeValue> chronoValues = this.chronoService.getGlobalChronotypeValues();
        UserComputationData userData = this.userService.getUserData(userId);

        System.out.println(meq.getMeqValue());
        ChronotypeEnum chronotype = computeChronotypeValue(meq.getMeqValue());
        GlobalChronotypeValue gcv = Objects.requireNonNull(chronoValues.stream()
                .filter(c -> c.getId() == chronotype.getId())
                .findFirst().orElse(null));


        SleepComputationForm scfe = new SleepComputationForm();
        scfe.setChronotype(chronotype);
        scfe.setChronoFa(computeChronoTypeVsRythm(psqi.getAverageLaydownTime(), gcv.getSleepFrom(), gcv.getSleepTo()));
        scfe.setChronoWa(computeChronoTypeVsRythm(psqi.getAverageTimeOfGettingUp(), gcv.getAwakeFrom(), gcv.getAwakeTo()));
        scfe.setLatencyFAGreater(psqi.getPsqilaten() > userData.getLatencyFaThreshold());
        // TODO: implement after SJL is converted to LocalTime
//        scfe.setSocJetlagGreater(mctq.getSJL() > userData.getSocJetlagThreshold());
        scfe.setSocJetlagGreater(mctq.getSJL() > 1.0);

        return scfe;
    }

    public SleepComputationForm computeInitial(String uid) {

        PsqiEvaluation psqi = formsEvalService.getNewestPsqi(uid);
        MctqEvaluation mctq = formsEvalService.getNewestMctq(uid);
        MeqEvaluation meq = formsEvalService.getNewestMeq(uid);
        System.out.println(meq);

        try {
            SleepComputationForm scfe = this.computeOverFormsData(mctq, meq, psqi, uid);
            System.out.println(scfe);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    private ChronoVsRythm computeChronoTypeVsRythm(String averageLaydownTime, LocalTime from, LocalTime to) {

        LocalTime timeValue = LocalTime.parse( averageLaydownTime, DateTimeFormatter.ISO_LOCAL_TIME );
        if (timeValue.isBefore(from)) {
            return ChronoVsRythm.EARLY;
        } else if(timeValue.isAfter(to)) {
            return ChronoVsRythm.LATER;
        } else {
            return ChronoVsRythm.OK;
        }

    }

    private ChronotypeEnum computeChronotypeValue(int meqVal) throws Exception {
        if (meqVal <= 30) {
            return ChronotypeEnum.STRONGLY_EVENING;
        } else if(meqVal < 41) {
            return ChronotypeEnum.WEAKLY_EVENING;
        } else if(meqVal < 58) {
            return ChronotypeEnum.AMBIVALENT;
        } else if(meqVal < 69) {
            return ChronotypeEnum.WEAKLY_MORNING;
        } else if (meqVal < 86) {
            return ChronotypeEnum.STRONGLY_MORNING;
        } else {
            throw new Exception();
        }
    }

}
