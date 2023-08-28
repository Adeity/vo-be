package cz.cvut.fel.vyzkumodolnosti.controllers;

import cz.cvut.fel.vyzkumodolnosti.handler.IncompleteFormsException;
import cz.cvut.fel.vyzkumodolnosti.handler.NoSuchResearchParticipantException;
import cz.cvut.fel.vyzkumodolnosti.model.dto.PageableRequestDto;
import cz.cvut.fel.vyzkumodolnosti.model.dto.computations.*;
import cz.cvut.fel.vyzkumodolnosti.model.dto.device.DeviceComputationFormDto;
import cz.cvut.fel.vyzkumodolnosti.model.dto.personsapi.MethodDto;
import cz.cvut.fel.vyzkumodolnosti.model.entities.ResearchParticipant;
import cz.cvut.fel.vyzkumodolnosti.model.entities.computations.GlobalChronotypeValue;
import cz.cvut.fel.vyzkumodolnosti.model.entities.computations.SleepComputationForm;
import cz.cvut.fel.vyzkumodolnosti.model.entities.computations.UserComputationData;
import cz.cvut.fel.vyzkumodolnosti.model.entities.device.DeviceComputationForm;
import cz.cvut.fel.vyzkumodolnosti.model.entities.forms.evaluations.MctqEvaluation;
import cz.cvut.fel.vyzkumodolnosti.model.entities.forms.evaluations.MeqEvaluation;
import cz.cvut.fel.vyzkumodolnosti.model.entities.forms.evaluations.PsqiEvaluation;
import cz.cvut.fel.vyzkumodolnosti.services.MethodService;
import cz.cvut.fel.vyzkumodolnosti.services.computations.*;
import cz.cvut.fel.vyzkumodolnosti.services.computations.mappers.SleepComputationFormMapper;
import cz.cvut.fel.vyzkumodolnosti.services.computations.mappers.UserComputationDataMapper;
import cz.cvut.fel.vyzkumodolnosti.services.computations.respondent.SleepRespondentService;
import cz.cvut.fel.vyzkumodolnosti.services.device.DeviceComputationService;
import cz.cvut.fel.vyzkumodolnosti.services.device.mappers.DeviceComputationFormMapper;
import cz.cvut.fel.vyzkumodolnosti.services.xls.ReportXlsExportService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping(value = "/comps")
public class ComputationsController {

    @Autowired
    SleepComputationFormMapper sleepComputationFormMapper;

    @Autowired
    private FormsEvalService formsEvalService;
    @Autowired
    private GlobalChronotypeValuesService chronoService;
    @Autowired
    private SleepComputationFormsService formsComputationService;

    @Autowired
    private DeviceComputationService deviceComputationService;

    @Autowired
    private UserComputationDataService userDataService;
    @Autowired
    private SleepRespondentService respondentService;

    @Autowired
    private DeviceComputationFormMapper deviceComputationFormMapper;

    @Autowired
    private ReportXlsExportService xlsService;

    @Autowired
    private UserComputationDataMapper userComputationDataMapper;

    @Autowired
    private MethodService methodService;

    @Autowired
    private ComputationUtilsService computationUtilsService;

    @GetMapping(value="/global-chrono")
    public List<GlobalChronotypeValue> getGlobalChronotypeValues() {
        return this.chronoService.getGlobalChronotypeValues();
    }

    @GetMapping(value="/recalculate/{id}")
    public SleepComputationForm recalculateForUser(@PathVariable("id") String userId) {

        log.info("recalculate for participant with research number " + userId);

        PsqiEvaluation psqi = formsEvalService.getNewestPsqi(userId);
        MctqEvaluation mctq = formsEvalService.getNewestMctq(userId);
        MeqEvaluation meq = formsEvalService.getNewestMeq(userId);

        return this.formsComputationService.computeOverFormsData(mctq, meq, psqi, userId);
    }

    @GetMapping(value="/comp/{id}")
    public SleepComputationForm getComputationForm(@PathVariable("id") String computationId) {
        long formId = Long.parseLong(computationId);
        return formsComputationService.getComputationForm(formId);
    }

    @GetMapping(value="/test/initial/{id}")
    public SleepComputationFormDto makeInitialComputation(@PathVariable("id") String researchNumber) {

        try {
            SleepComputationForm entity = formsComputationService.computeStandard(researchNumber, 1);
            return sleepComputationFormMapper.entityToDto(entity);
        } catch(IncompleteFormsException e) {
            log.error("Exception occured! " + e.getMessage());
            return null;
        }
    }

    @GetMapping(value="/test/weekly-device/{id}")
    public DeviceComputationFormDto computeWeeklyFromDevice(@PathVariable("id") String researchNumber) {

        var researchParticipant = this.computationUtilsService.getResearchParticipantByResearchNumber(researchNumber);

        try {
            DeviceComputationForm deviceComputationForm = deviceComputationService.computeStandard(researchParticipant);
            if (deviceComputationForm == null)
                return null;
            return deviceComputationFormMapper.entityToDto(deviceComputationForm);
        } catch(IncompleteFormsException e) {
            log.error("Exception occured! " + e.getMessage());
            return null;
        }
    }

    @PostMapping(value="/update-form-computation")
    public RespondentsResponseDto updateComputationResultTexts(@Valid @RequestBody UpdateSleepComputationFormDto dto) {

        SleepComputationForm scfe = this.sleepComputationFormMapper.dtoToEntity(dto.getData());
        this.formsComputationService.updateComputationFormTexts(scfe);
        return respondentService.getRespondentDataPageable(dto.getPageInfo());
    }
    @PostMapping(value="/update-device-computation")
    public RespondentsResponseDto updateComputationResultTexts(@Valid @RequestBody UpdateDeviceComputationFormDto dto) {

        DeviceComputationForm dcfe = this.deviceComputationFormMapper.dtoToEntity(dto.getData());
        this.deviceComputationService.updateComputationFormTexts(dcfe);
        return respondentService.getRespondentDataPageable(dto.getPageInfo());
    }

    @PostMapping(value="/update-u-data")
    public RespondentsResponseDto updateUserDataAndRecalculateForUser(@Valid @RequestBody UpdateUserDataDto data) {

        log.info("Updating User Computation Data and recalculating for respondent with rn " + data.getData().getResearchNumber() + " " + data.getData().getSocJetlagThreshold());
        UserComputationData ucd = this.userComputationDataMapper.dtoToEntity(data.getData());
        this.userDataService.updateUserComputationData(ucd);

        this.formsComputationService.relalculateForUser(data.getData().getResearchNumber());
        this.deviceComputationService.recalculateForUser(data.getData().getResearchNumber());

        return respondentService.getRespondentDataPageable(data.getPageInfo());
    }

    @PostMapping(value = "/update-global-data")
    public RespondentsResponseDto updateGlobalChronoDataAndRecalculateForSleep(@Valid @RequestBody UpdateGlobalChronoValuesDto data) {

        data.getData().forEach(dto -> this.chronoService.updateGlobalChronotypeValue(dto));
        this.formsComputationService.recalculateForEveryone();
        this.deviceComputationService.recalculateForEveryone();
        return respondentService.getRespondentDataPageable(data.getPageInfo());
    }

    @GetMapping(value="/get-user-data/{id}")
    public UserComputationDataDto getRespondentSleepGlobalData(@PathVariable("id") String uid) {
        return this.userDataService.getUserDataDto(uid);
    }

    @GetMapping(value="/sleep-respondent-data")
    public List<SleepRespondentDto> getSleepRespondentsData() {
        return respondentService.getRespondentData();
    }

    @PostMapping(value = "/sleep-respondent-data-pageable")
    public RespondentsResponseDto getSleepRespondentsData(@RequestBody PageableRequestDto request) {

        return respondentService.getRespondentDataPageable(request);
    }

    @GetMapping(value="/get-methods")
    public List<MethodDto> getMethods() {
        return this.methodService.getAllMethods();
    }


    @GetMapping(value="/export-to-xls/{id}")
    public ResponseEntity<Resource> exportRespondentToXls(@PathVariable("id") String uid) {
        try {
            Resource resource = xlsService.exportReportsToXls(uid);
            return ResponseEntity.ok()
                    .body(resource);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping(value="/export-to-xls")
    public ResponseEntity<Resource> exportMultipleRespondentsToXls(@RequestBody List<String> respIds) {
        try {
            Resource resource = xlsService.exportReportsToXlsForSelected(respIds);
            return ResponseEntity.ok()
                    .body(resource);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping(value="/export-all-to-xls")
    public ResponseEntity<Resource> exportAllToXls() {
        try {
            Resource resource = xlsService.exportReportsToXlsForAll();
            return ResponseEntity.ok()
                    .body(resource);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping(value = "/export-unregistered")
    public ResponseEntity<Resource> exportUnregistered() {
        try {
            log.info("Exporting forms from unregistered people to xls!");
            Resource resource = xlsService.exportUnregisteredToXls();
            return ResponseEntity.ok()
                    .body(resource);
        } catch (IOException e) {
            throw  new RuntimeException(e);
        }
    }
}
