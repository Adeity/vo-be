package cz.cvut.fel.vyzkumodolnosti.controllers;

import cz.cvut.fel.vyzkumodolnosti.model.dto.computations.*;
import cz.cvut.fel.vyzkumodolnosti.model.entities.computations.GlobalChronotypeValue;
import cz.cvut.fel.vyzkumodolnosti.model.entities.computations.SleepComputationForm;
import cz.cvut.fel.vyzkumodolnosti.model.entities.computations.UserComputationData;
import cz.cvut.fel.vyzkumodolnosti.model.entities.forms.evaluations.MctqEvaluation;
import cz.cvut.fel.vyzkumodolnosti.model.entities.forms.evaluations.MeqEvaluation;
import cz.cvut.fel.vyzkumodolnosti.model.entities.forms.evaluations.PsqiEvaluation;
import cz.cvut.fel.vyzkumodolnosti.services.computations.FormsEvalService;
import cz.cvut.fel.vyzkumodolnosti.services.computations.GlobalChronotypeValuesService;
import cz.cvut.fel.vyzkumodolnosti.services.computations.SleepComputationFormsService;
import cz.cvut.fel.vyzkumodolnosti.services.computations.UserComputationDataService;
import cz.cvut.fel.vyzkumodolnosti.services.computations.mappers.SleepComputationFormMapper;
import cz.cvut.fel.vyzkumodolnosti.services.computations.mappers.UserComputationDataMapper;
import cz.cvut.fel.vyzkumodolnosti.services.computations.respondent.SleepRespondentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.Serializable;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping(value = "/comps")
public class ComputationsController {

    @Autowired
    private FormsEvalService formsEvalService;
    @Autowired
    private GlobalChronotypeValuesService chronoService;
    @Autowired
    private SleepComputationFormsService computationService;

    @Autowired
    private UserComputationDataService userDataService;
    @Autowired
    private SleepRespondentService respondentService;


    @GetMapping(value = "/id")
    public List<PsqiEvaluation> getEvaluatedForms() {
//        List<PsqiEvaluation> results = formsEvalService.getAllPsqiEvalsById(100);
//        return new ArrayList<>();
//        System.out.println(results.toString());
        return formsEvalService.getAllPsqiEvalsById(100);
    }

    @GetMapping(value="/global-chrono")
    public List<GlobalChronotypeValue> getGlobalChronotypeValues() {
        return this.chronoService.getGlobalChronotypeValues();
    }

    @GetMapping(value="/recalculate/{id}")
    public SleepComputationForm recalculateForUser(@PathVariable("id") String userId) throws Exception {
        PsqiEvaluation psqi = formsEvalService.getNewestPsqi(userId);
        MctqEvaluation mctq = formsEvalService.getNewestMctq(userId);
        MeqEvaluation meq = formsEvalService.getNewestMeq(userId);

        return this.computationService.computeOverFormsData(mctq, meq, psqi, userId);
    }

    @GetMapping(value="/comp/{id}")
    public SleepComputationForm getComputationForm(@PathVariable("id") String computationId) {
        long formId = Long.parseLong(computationId);
        return computationService.getComputationForm(formId);
    }

    @GetMapping(value="/test/initial/{id}")
    public SleepComputationForm makeInitialComputation(@PathVariable("id") String uid) {

        return computationService.computeStandard(uid);
    }

    @PostMapping(value="/update-computation")
    public List<SleepRespondentDto> updateComputationResultTexts(@Valid @RequestBody SleepComputationFormDto dto) {

        SleepComputationForm scfe = new SleepComputationFormMapper().dtoToEntity(dto);
        this.computationService.updateComputationFormTexts(scfe);
        return respondentService.getRespondentData();
    }

    @PostMapping(value="/update-u-data")
    public List<SleepRespondentDto> updateUserDataAndRecalculateForUser(@Valid @RequestBody UpdateUserDataDto data) {

        UserComputationData ucd = new UserComputationDataMapper().dtoToEntity(data);
        this.userDataService.updateUserComputationData(ucd);

        this.computationService.relalculateForUser(data.getUserId());
        return respondentService.getRespondentData();
    }

    @PostMapping(value = "/update-global-data")
    public List<SleepRespondentDto> updateGlobalChronoDataAndRecalculateForSleep(@Valid @RequestBody List<SingleGlobalValueDto> data) {

        data.forEach(dto -> this.chronoService.updateGlobalChronotypeValue(dto));
        this.computationService.recalculateForEveryone();
        return respondentService.getRespondentData();
    }

    @GetMapping(value="/get-user-data/{id}")
    public UpdateUserDataDto getRespondentSleepGlobalData(@PathVariable("id") String uid) {
        return this.userDataService.getUserDataDto(uid);
    }

    @GetMapping(value="/sleep-respondent-data")
    public List<SleepRespondentDto> getSleepRespondentsData() {
        return respondentService.getRespondentData();
    }

}
