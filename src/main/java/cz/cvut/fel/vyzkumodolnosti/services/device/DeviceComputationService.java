package cz.cvut.fel.vyzkumodolnosti.services.device;

import cz.cvut.fel.vyzkumodolnosti.handler.IllegalMeqValueException;
import cz.cvut.fel.vyzkumodolnosti.handler.IncompleteFormsException;
import cz.cvut.fel.vyzkumodolnosti.handler.NoSuchResearchParticipantException;
import cz.cvut.fel.vyzkumodolnosti.model.domain.computations.ChronotypeEnum;
import cz.cvut.fel.vyzkumodolnosti.model.dto.device.DeviceSleepEvaluationDto;
import cz.cvut.fel.vyzkumodolnosti.model.entities.ResearchParticipant;
import cz.cvut.fel.vyzkumodolnosti.model.entities.computations.GlobalChronotypeValue;
import cz.cvut.fel.vyzkumodolnosti.model.entities.computations.UserComputationData;
import cz.cvut.fel.vyzkumodolnosti.model.entities.device.DeviceComputationForm;
import cz.cvut.fel.vyzkumodolnosti.model.entities.device.DeviceSleepEvaluation;
import cz.cvut.fel.vyzkumodolnosti.model.entities.forms.evaluations.MeqEvaluation;
import cz.cvut.fel.vyzkumodolnosti.model.entities.forms.evaluations.PsqiEvaluation;
import cz.cvut.fel.vyzkumodolnosti.repository.device.DeviceComputationFormRepository;
import cz.cvut.fel.vyzkumodolnosti.repository.forms.ResearchParticipantRepository;
import cz.cvut.fel.vyzkumodolnosti.services.computations.ComputationUtilsService;
import cz.cvut.fel.vyzkumodolnosti.services.computations.FormsEvalService;
import cz.cvut.fel.vyzkumodolnosti.services.computations.GlobalChronotypeValuesService;
import cz.cvut.fel.vyzkumodolnosti.services.computations.UserComputationDataService;
import cz.cvut.fel.vyzkumodolnosti.services.device.mappers.DeviceSleepEvaluationMapper;
import cz.cvut.fel.vyzkumodolnosti.utils.TimeConversionUtils;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DeviceComputationService {

    @Autowired
    DeviceEvaluationService deviceEvaluationService;

    @Autowired
    private GlobalChronotypeValuesService chronoService;
    @Autowired
    private UserComputationDataService userService;

    @Autowired
    private FormsEvalService formsEvalService;

    @Autowired
    private ComputationUtilsService compUtilsService;

    @Autowired
    private DeviceComputationFormRepository deviceComputationFormRepository;

    @Autowired
    private DeviceSleepEvaluationMapper mapper;

    @Autowired
    private ResearchParticipantRepository researchParticipantRepository;

    public DeviceComputationForm computeStandard(ResearchParticipant researchParticipant) throws IncompleteFormsException {

        DeviceComputationForm dcfe = this.computeFromNewest(researchParticipant);
        if(dcfe != null) {
            dcfe.setRecalculations(0);
            deviceComputationFormRepository.save(dcfe);
        }

        return dcfe;
    }

    public DeviceComputationForm computeOverDeviceData(DeviceSleepEvaluation device, MeqEvaluation meq, PsqiEvaluation psqi, String researchNumber) {

        DeviceSleepEvaluationDto deviceEval = mapper.entityToDto(device);

        List<GlobalChronotypeValue> chronoValues = this.chronoService.getGlobalChronotypeValues();
        UserComputationData userData = this.userService.getUserData(researchNumber);

        ResearchParticipant researchParticipant = this.compUtilsService.getResearchParticipantByResearchNumber(researchNumber);

        DeviceComputationForm dcfe = new DeviceComputationForm(researchParticipant);
        dcfe.setSocJetlagThreshold(userData.getSocJetlagThreshold());
        dcfe.setLatencyFaThreshold(userData.getLatencyFaThreshold());

        ChronotypeEnum chronotype = this.compUtilsService.computeChronotypeValue(meq.getMeqValue());
        GlobalChronotypeValue gcv = Objects.requireNonNull(chronoValues.stream()
                .filter(c -> c.getId() == chronotype.getId())
                .findFirst().orElse(null));

        dcfe.setChronotype(chronotype);
        dcfe.setAwakeFrom(gcv.getAwakeFrom());
        dcfe.setAwakeTo(gcv.getAwakeTo());
        dcfe.setSleepFrom(gcv.getSleepFrom());
        dcfe.setSleepTo(gcv.getSleepTo());

        dcfe.setLatency(psqi.getPsqilaten());
        dcfe.setSocJetlag(TimeConversionUtils.sjlFromDoubleToString(deviceEval.getSocJetlag()));

        dcfe.setAvgFallAsleepTimeWorkDays(deviceEval.getAvgFallAsleepTimeWorkDays());
        dcfe.setAvgWakingTimeWorkDays(deviceEval.getAvgWakingTimeWorkDays());
        dcfe.setAvgFallAsleepTimeFreeDays(deviceEval.getAvgFallAsleepTimeFreeDays());
        dcfe.setAvgWakingTimeFreeDays(deviceEval.getAvgWakingTimeFreeDays());

        dcfe.setChronoFaWorkDays(this.compUtilsService.computeChronoTypeVsRythmFa(deviceEval.getAvgFallAsleepTimeWorkDays(), gcv.getSleepFrom(), gcv.getSleepTo()));
        dcfe.setChronoWaWorkDays(this.compUtilsService.computeChronoTypeVsRythmWa(deviceEval.getAvgWakingTimeWorkDays(), gcv.getAwakeFrom(), gcv.getAwakeTo()));
        dcfe.setChronoFaFreeDays(this.compUtilsService.computeChronoTypeVsRythmFa(deviceEval.getAvgFallAsleepTimeFreeDays(), gcv.getSleepFrom(), gcv.getSleepTo()));
        dcfe.setChronoWaFreeDays(this.compUtilsService.computeChronoTypeVsRythmWa(deviceEval.getAvgWakingTimeFreeDays(), gcv.getAwakeFrom(), gcv.getAwakeTo()));
        dcfe.setLatencyFAGreater(psqi.getPsqilaten() > userData.getLatencyFaThreshold());
        dcfe.setSocJetlagGreater(deviceEval.getSocJetlag() > userData.getSocJetlagThreshold().toSecondOfDay());

        return dcfe;
    }

    private DeviceComputationForm computeFromNewest(ResearchParticipant researchParticipant) throws IncompleteFormsException {

        PsqiEvaluation psqi = formsEvalService.getNewestPsqi(researchParticipant.getResearchNumber());
        MeqEvaluation meq = formsEvalService.getNewestMeq(researchParticipant.getResearchNumber());
        DeviceSleepEvaluation deviceEval = deviceEvaluationService.evaluateLastWeekSleeps(researchParticipant);

        if (deviceEval == null)
            return null;

        deviceEvaluationService.saveEvaluation(deviceEval);
        if (psqi == null || meq == null) {
            String message = "Incomplete forms!"
                    + (psqi == null ? " PSQI is missing!" : "")
                    + (meq == null ? " MEQ is missing!" : "");
            throw new IncompleteFormsException(message);
        }

        DeviceComputationForm dcfe;
        try {
            dcfe = this.computeOverDeviceData(deviceEval, meq, psqi, researchParticipant.getResearchNumber());
            dcfe.setVersion(deviceEval.getVersion());
            this.compUtilsService.setDefaultTexts(dcfe);
        } catch (Exception e) {
            throw new IllegalMeqValueException(e);
        }

        return dcfe;
    }

    public void recalculateForUser(String researchNumber) {
        ArrayList<DeviceComputationForm> computationForms = new ArrayList<>(deviceComputationFormRepository.findAllByResearchParticipantResearchNumberAndRecalculations(researchNumber, 0));
        computationForms.forEach(this::recalculateForForm);
    }

    public void recalculateForEveryone() {
        ArrayList<DeviceComputationForm> computationForms = new ArrayList<>(deviceComputationFormRepository.findAllByRecalculations(0));
        computationForms.forEach(this::recalculateForForm);
    }

    private void recalculateForForm(DeviceComputationForm form) {

        PsqiEvaluation psqi = formsEvalService.findNewestPsqiClosesToDate(form.getResearchParticipant().getResearchNumber(), form.getCreated());
        MeqEvaluation meq = formsEvalService.findNewestMeqClosesToDate(form.getResearchParticipant().getResearchNumber(), form.getCreated());
        DeviceSleepEvaluation deviceEval = deviceEvaluationService.findNewestEvaluationClosestToDate(form.getResearchParticipant().getResearchNumber(), form.getCreated());

        try {
            assert meq != null;
            assert psqi != null;
            assert deviceEval != null;

            DeviceComputationForm dcfe = this.computeOverDeviceData(deviceEval, meq, psqi, form.getResearchParticipant().getResearchNumber());
            dcfe.setCreated(form.getCreated());
            dcfe.setVersion(form.getVersion());
            dcfe.setRecalculations(form.getRecalculations() + 1);
            dcfe.setRecalculations(deviceComputationFormRepository.countAllByVersion(form.getVersion()));
            this.compUtilsService.updateScfeTexts(form, dcfe);

            deviceComputationFormRepository.save(dcfe);
        } catch (Exception e) {
            throw new IllegalMeqValueException(e);
        }
    }

    public void updateComputationFormTexts(DeviceComputationForm dcfe) {
        dcfe.setModified(new Date());
        this.deviceComputationFormRepository.save(dcfe);
    }
}
