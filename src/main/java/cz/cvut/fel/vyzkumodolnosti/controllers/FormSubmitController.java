package cz.cvut.fel.vyzkumodolnosti.controllers;

import cz.cvut.fel.vyzkumodolnosti.model.dto.forms.submitted.LifeSatisfactionSubmittedFormDto;
import cz.cvut.fel.vyzkumodolnosti.model.dto.forms.submitted.MctqSubmittedFormDto;
import cz.cvut.fel.vyzkumodolnosti.model.dto.forms.submitted.MeqSubmittedFormDto;
import cz.cvut.fel.vyzkumodolnosti.model.dto.forms.submitted.PsqiSubmittedFormDto;
import cz.cvut.fel.vyzkumodolnosti.services.forms.impl.SubmittedFormWriteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/form-submit")
public class FormSubmitController {
    private final SubmittedFormWriteServiceImpl writeService;

    @Autowired
    public FormSubmitController(SubmittedFormWriteServiceImpl writeService) {
        this.writeService = writeService;
    }

    @PostMapping(value = "/psqi")
    public void submitPsqi(@Valid @RequestBody PsqiSubmittedFormDto dto) {
        writeService.save(dto);
    }

    @PostMapping(value = "/mctq")
    public void submitPsqi(@Valid @RequestBody MctqSubmittedFormDto dto) {
        writeService.save(dto);
    }

    @PostMapping(value = "/meq")
    public void submitPsqi(@Valid @RequestBody MeqSubmittedFormDto dto) {
        writeService.save(dto);
    }

    @PostMapping(value = "/lifesat")
    public void submitPsqi(@Valid @RequestBody LifeSatisfactionSubmittedFormDto dto) {
        writeService.save(dto);
    }
}
