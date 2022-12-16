package cz.cvut.fel.vyzkumodolnosti.controllers;

import cz.cvut.fel.vyzkumodolnosti.controllers.errormodel.ErrorModel;
import cz.cvut.fel.vyzkumodolnosti.controllers.errormodel.ErrorResponse;
import cz.cvut.fel.vyzkumodolnosti.model.dto.forms.submitted.*;
import cz.cvut.fel.vyzkumodolnosti.services.forms.impl.SubmittedFormWriteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

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
    public void submitMctq(@Valid @RequestBody MctqSubmittedFormDto dto) {
        writeService.save(dto);
    }

    @PostMapping(value = "/meq")
    public void submitMeq(@Valid @RequestBody MeqSubmittedFormDto dto) {
        writeService.save(dto);
    }

    @PostMapping(value = "/life-satisfaction")
    public void submitLifeSatisfaction(@Valid @RequestBody LifeSatisfactionSubmittedFormDto dto) {
        writeService.save(dto);
    }

    @PostMapping(value = "/pss")
    public void submitMeq(@Valid @RequestBody PssSubmittedFormDto dto) {
        writeService.save(dto);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleException(MethodArgumentNotValidException exception) {
        List<ErrorModel> errorMessages = exception.getBindingResult().getFieldErrors().stream()
                .map(err -> new ErrorModel(err.getField(), err.getRejectedValue(), err.getDefaultMessage()))
                .distinct()
                .collect(Collectors.toList());
        return new ErrorResponse().builder().errorMessage(errorMessages).build();
    }
}
