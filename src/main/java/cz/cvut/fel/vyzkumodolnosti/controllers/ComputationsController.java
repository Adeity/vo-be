package cz.cvut.fel.vyzkumodolnosti.controllers;

import cz.cvut.fel.vyzkumodolnosti.model.entities.computations.GlobalChronotypeValue;
import cz.cvut.fel.vyzkumodolnosti.model.entities.computations.SleepComputationForm;
import cz.cvut.fel.vyzkumodolnosti.model.entities.forms.evaluations.MctqEvaluation;
import cz.cvut.fel.vyzkumodolnosti.model.entities.forms.evaluations.MeqEvaluation;
import cz.cvut.fel.vyzkumodolnosti.model.entities.forms.evaluations.PsqiEvaluation;
import cz.cvut.fel.vyzkumodolnosti.services.computations.FormsEvalService;
import cz.cvut.fel.vyzkumodolnosti.services.computations.GlobalChronotypeValuesService;
import cz.cvut.fel.vyzkumodolnosti.services.computations.SleepComputationFormsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        System.out.println("Yoo");
        return computationService.getComputationForm(formId);
    }

    @GetMapping(value="/test/initial/{id}")
    public SleepComputationForm makeInitialComputation(@PathVariable("id") String uid) {

        return computationService.computeInitial(uid);
    }

}
