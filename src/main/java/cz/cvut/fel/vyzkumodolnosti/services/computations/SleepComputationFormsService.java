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
import cz.cvut.fel.vyzkumodolnosti.services.TimeComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class SleepComputationFormsService {

    private static final String LATENCY_FA_GREATER_DEFAULT_TEXT = "Větší než";
    private static final String LATENCY_FA_LOWER_DEFAULT_TEXT = "Menší než";
    private static final String SOC_JL_GREATER_DEFAULT_TEXT = "Větší než";
    private static final String SOC_JL_LOWER_DEFAULT_TEXT = "Menší než";

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

        ChronotypeEnum chronotype = computeChronotypeValue(meq.getMeqValue());
        GlobalChronotypeValue gcv = Objects.requireNonNull(chronoValues.stream()
                .filter(c -> c.getId() == chronotype.getId())
                .findFirst().orElse(null));

        TimeComponent timeComponent = new TimeComponent();

        SleepComputationForm scfe = new SleepComputationForm(userId);
        scfe.setChronotype(chronotype);
        scfe.setAwakeFrom(gcv.getAwakeFrom());
        scfe.setAwakeTo(gcv.getAwakeTo());
        scfe.setSleepFrom(gcv.getSleepFrom());
        scfe.setSleepTo(gcv.getSleepTo());

        scfe.setChronoFa(computeChronoTypeVsRythm(psqi.getAverageLaydownTime(), gcv.getSleepFrom(), gcv.getSleepTo()));
        scfe.setChronoWa(computeChronoTypeVsRythm(psqi.getAverageTimeOfGettingUp(), gcv.getAwakeFrom(), gcv.getAwakeTo()));
        scfe.setLatencyFAGreater(psqi.getPsqilaten() > userData.getLatencyFaThreshold());
        scfe.setSocJetlagGreater(
                timeComponent.hourMinuteFormatToSeconds(mctq.getSJL()) > userData.getSocJetlagThreshold().toSecondOfDay());

        return scfe;
    }

    private void setDefaultTexts(SleepComputationForm scfe) {

        scfe.setChronoFaText(scfe.getChronoFa().getDefaultTextFa());
        scfe.setChronoWaText(scfe.getChronoWa().getDefaultTextWa());
        scfe.setLatencyFAGreaterText(scfe.isLatencyFAGreater() ? LATENCY_FA_GREATER_DEFAULT_TEXT : LATENCY_FA_LOWER_DEFAULT_TEXT);
        scfe.setSocJetlagGreaterText(scfe.isSocJetlagGreater() ? SOC_JL_GREATER_DEFAULT_TEXT : SOC_JL_LOWER_DEFAULT_TEXT);
    }

    private void updateScfeTexts(SleepComputationForm previous, SleepComputationForm recalculated) {
        if(previous.getChronoFa() == recalculated.getChronoFa())
            recalculated.setChronoFaText(previous.getChronoFaText());
        else recalculated.setChronoFaText(recalculated.getChronoFa().getDefaultTextFa());

        if(previous.getChronoWa() == recalculated.getChronoWa())
            recalculated.setChronoWaText(previous.getChronoWaText());
        else recalculated.setChronoWaText(recalculated.getChronoWa().getDefaultTextWa());

        if(previous.isLatencyFAGreater() == recalculated.isLatencyFAGreater())
            recalculated.setLatencyFAGreaterText(previous.getLatencyFAGreaterText());
        else recalculated.setLatencyFAGreaterText(recalculated.isLatencyFAGreater() ? LATENCY_FA_GREATER_DEFAULT_TEXT : LATENCY_FA_LOWER_DEFAULT_TEXT);

        if(previous.isSocJetlagGreater() == recalculated.isSocJetlagGreater())
            recalculated.setSocJetlagGreaterText(previous.getSocJetlagGreaterText());
        else recalculated.setSocJetlagGreaterText(recalculated.isSocJetlagGreater() ? SOC_JL_GREATER_DEFAULT_TEXT : SOC_JL_LOWER_DEFAULT_TEXT);
    }

    private SleepComputationForm computeFromNewest(String uid) {

        PsqiEvaluation psqi = formsEvalService.getNewestPsqi(uid);
        MctqEvaluation mctq = formsEvalService.getNewestMctq(uid);
        MeqEvaluation meq = formsEvalService.getNewestMeq(uid);

        SleepComputationForm scfe;
        try {
            scfe = this.computeOverFormsData(mctq, meq, psqi, uid);
            this.setDefaultTexts(scfe);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return scfe;
    }

    public SleepComputationForm computeStandard(String uid) {

        SleepComputationForm scfe = this.computeFromNewest(uid);
        if(scfe != null) {
            scfe.setId(6385754L);
            scfe.setSocJetlagGreaterText("kek");
            repository.save(scfe);
        }

        return scfe;
    }

    public SleepComputationForm computeWithComparison(String uid) {
        SleepComputationForm scfe = this.computeFromNewest(uid);
//        scfe.setCompComparison();
        if(scfe != null) {
            repository.save(scfe);
        }
        return scfe;
    }

    public void relalculateForUser(String uid) {
        ArrayList<SleepComputationForm> computationForms = new ArrayList<>(repository.findAllByPersonId(uid));
        computationForms.forEach(this::recalculateForForm);
    }

    private void recalculateForForm(SleepComputationForm form) {

        PsqiEvaluation psqi = formsEvalService.findNewestPsqiClosesToDate(form.getPersonId(), form.getCreated());
        MctqEvaluation mctq = formsEvalService.findNewestMctqClosesToDate(form.getPersonId(), form.getCreated());
        MeqEvaluation meq = formsEvalService.findNewestMeqClosesToDate(form.getPersonId(), form.getCreated());

        if(psqi == null || mctq == null || meq == null) {
            System.out.println("Null!");
            System.out.println(psqi);
            System.out.println(mctq);
            System.out.println(meq);
        }

        try {
            assert mctq != null;
            assert meq != null;
            assert psqi != null;

            SleepComputationForm scfe = this.computeOverFormsData(mctq, meq, psqi, form.getPersonId());
            scfe.setCreated(form.getCreated());
            this.updateScfeTexts(form, scfe);
            System.out.println(scfe.getChronoFaText());

            repository.save(scfe);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void recalculateForEveryone() {
        ArrayList<SleepComputationForm> computationForms = new ArrayList<>(repository.findAll());
        computationForms.forEach(this::recalculateForForm);
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

    public void updateComputationFormTexts(SleepComputationForm scfe) {
        this.repository.save(scfe);
    }
}
