package cz.cvut.fel.vyzkumodolnosti.services.computations;

import cz.cvut.fel.vyzkumodolnosti.handler.IllegalMeqValueException;
import cz.cvut.fel.vyzkumodolnosti.handler.IncompleteFormsException;
import cz.cvut.fel.vyzkumodolnosti.model.domain.computations.ChronotypeEnum;
import cz.cvut.fel.vyzkumodolnosti.model.entities.ResearchParticipant;
import cz.cvut.fel.vyzkumodolnosti.model.entities.computations.GlobalChronotypeValue;
import cz.cvut.fel.vyzkumodolnosti.model.entities.computations.SleepComputationForm;
import cz.cvut.fel.vyzkumodolnosti.model.entities.computations.UserComputationData;
import cz.cvut.fel.vyzkumodolnosti.model.entities.forms.evaluations.MctqEvaluation;
import cz.cvut.fel.vyzkumodolnosti.model.entities.forms.evaluations.MeqEvaluation;
import cz.cvut.fel.vyzkumodolnosti.model.entities.forms.evaluations.PsqiEvaluation;
import cz.cvut.fel.vyzkumodolnosti.repository.computations.SleepComputationFormRepository;
import cz.cvut.fel.vyzkumodolnosti.services.TimeComponent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class SleepComputationFormsService {

    @Autowired
    private GlobalChronotypeValuesService chronoService;
    @Autowired
    private UserComputationDataService userService;

    @Autowired
    private FormsEvalService formsEvalService;

    @Autowired
    private ComputationUtilsService compUtilsService;

    @Autowired
    private SleepComputationFormRepository repository;


    public SleepComputationForm getComputationForm(long id) {
        return this.repository.findById(id);
    }

    public SleepComputationForm computeOverFormsData(MctqEvaluation mctq, MeqEvaluation meq, PsqiEvaluation psqi, String researchNumber) throws IllegalMeqValueException {

        List<GlobalChronotypeValue> chronoValues = this.chronoService.getGlobalChronotypeValues();
        UserComputationData userData = this.userService.getUserData(researchNumber);

        ResearchParticipant researchParticipant = this.compUtilsService.getResearchParticipantByResearchNumber(researchNumber);

        SleepComputationForm scfe = new SleepComputationForm(researchParticipant);
        scfe.setSocJetlagThreshold(userData.getSocJetlagThreshold());
        scfe.setLatencyFaThreshold(userData.getLatencyFaThreshold());

        ChronotypeEnum chronotype = this.compUtilsService.computeChronotypeValue(meq.getMeqValue());
        GlobalChronotypeValue gcv = Objects.requireNonNull(chronoValues.stream()
                .filter(c -> c.getId() == chronotype.getId())
                .findFirst().orElse(null));


        String socJetlag = mctq == null ? psqi.getSJL() : mctq.getSJL();

        scfe.setChronotype(chronotype);
        scfe.setAwakeFrom(gcv.getAwakeFrom());
        scfe.setAwakeTo(gcv.getAwakeTo());
        scfe.setSleepFrom(gcv.getSleepFrom());
        scfe.setSleepTo(gcv.getSleepTo());
        scfe.setAvgLaydownTime(psqi.getAverageLaydownTime());
        scfe.setAvgWakingTime(psqi.getAverageTimeOfGettingUp());

        scfe.setLatency(psqi.getPsqilaten());
        scfe.setSocJetlag(socJetlag);
        scfe.setChronoFa(this.compUtilsService.computeChronoTypeVsRythmFa(psqi.getAverageLaydownTime(), gcv.getSleepFrom(), gcv.getSleepTo()));
        scfe.setChronoWa(this.compUtilsService.computeChronoTypeVsRythmWa(psqi.getAverageTimeOfGettingUp(), gcv.getAwakeFrom(), gcv.getAwakeTo()));
        scfe.setLatencyFAGreater(psqi.getPsqilaten() > userData.getLatencyFaThreshold());
        scfe.setSocJetlagGreater(
                new TimeComponent().hourMinuteFormatToSeconds(socJetlag) > userData.getSocJetlagThreshold().toSecondOfDay());

        return scfe;
    }

    private SleepComputationForm computeFromNewest(String researchNumber) throws IncompleteFormsException {

        PsqiEvaluation psqi = formsEvalService.getNewestPsqi(researchNumber);
        MctqEvaluation mctq = formsEvalService.getNewestMctq(researchNumber);
        MeqEvaluation meq = formsEvalService.getNewestMeq(researchNumber);

        if (psqi == null || meq == null) {
            String message = "Incomplete forms!"
                    + (psqi == null ? " PSQI is missing!" : "")
                    + (meq == null ? " MEQ is missing!" : "");
            throw new IncompleteFormsException(message);
        }

        SleepComputationForm scfe;
        try {
            scfe = this.computeOverFormsData(mctq, meq, psqi, researchNumber);
            this.compUtilsService.setDefaultTexts(scfe);
        } catch (Exception e) {
            throw new IllegalMeqValueException(e);
        }

        return scfe;
    }

    public SleepComputationForm computeStandard(String researchNumber, int version) throws IncompleteFormsException {

        SleepComputationForm scfe = this.computeFromNewest(researchNumber);
        if(scfe != null) {
            scfe.setVersion(version);
            scfe.setRecalculations(0);
            repository.save(scfe);
        }

        return scfe;
    }

    public SleepComputationForm computeWithComparison(String researchNumber) throws IncompleteFormsException {
        SleepComputationForm scfe = this.computeFromNewest(researchNumber);
//        scfe.setCompComparison();
        if(scfe != null) {
            scfe.setVersion(3);
            repository.save(scfe);
        }
        return scfe;
    }

    public void compute(String researchNumber) {

        log.info("Computation from forms for research number " + researchNumber + " started.");
        int version = (int) this.formsEvalService.getPsqiCount(researchNumber);

        try {
            if(version == 3) computeWithComparison(researchNumber);
            else computeStandard(researchNumber, version);

            log.info("Computation from forms for research number " + researchNumber + " ended successfully!");
        } catch(IncompleteFormsException e) {
            log.warn("Incomplete forms for researchNumber " + researchNumber + "!");
        }
    }

    public void relalculateForUser(String researchNumber) {
        ArrayList<SleepComputationForm> computationForms = new ArrayList<>(repository.findAllByResearchParticipantResearchNumberAndRecalculations(researchNumber, 0));
        computationForms.forEach(this::recalculateForForm);
    }

    private void recalculateForForm(SleepComputationForm form) {

        PsqiEvaluation psqi = formsEvalService.findNewestPsqiClosesToDate(form.getPersonId(), form.getCreated());
        MctqEvaluation mctq = formsEvalService.findNewestMctqClosesToDate(form.getPersonId(), form.getCreated());
        MeqEvaluation meq = formsEvalService.findNewestMeqClosesToDate(form.getPersonId(), form.getCreated());

        try {
            if (psqi == null || meq == null) {
                String message = "Incomplete forms!"
                        + (psqi == null ? " PSQI is missing!" : "")
                        + (meq == null ? " MEQ is missing!" : "");
                throw new IncompleteFormsException(message);
            }

            SleepComputationForm scfe = this.computeOverFormsData(mctq, meq, psqi, form.getPersonId());
            scfe.setCreated(form.getCreated());
            scfe.setVersion(form.getVersion());
            scfe.setRecalculations(repository.countAllByVersionAndResearchParticipantResearchNumber(form.getVersion(), form.getResearchParticipant().getResearchNumber()));
            this.compUtilsService.updateScfeTexts(form, scfe);

            repository.save(scfe);
        } catch (IllegalMeqValueException e) {
            throw new IllegalMeqValueException(e);
        } catch (IncompleteFormsException e) {
            throw new IncompleteFormsException(e);
        }
    }

    public void recalculateForEveryone() {
        ArrayList<SleepComputationForm> computationForms = new ArrayList<>(repository.findAllByRecalculations(0));
        computationForms.forEach(this::recalculateForForm);
    }

    public void updateComputationFormTexts(SleepComputationForm scfe) {
        scfe.setModified(new Date());
        this.repository.save(scfe);
    }
}
