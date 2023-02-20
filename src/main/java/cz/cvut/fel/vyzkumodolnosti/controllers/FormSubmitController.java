package cz.cvut.fel.vyzkumodolnosti.controllers;

import cz.cvut.fel.vyzkumodolnosti.controllers.errormodel.ErrorModel;
import cz.cvut.fel.vyzkumodolnosti.controllers.errormodel.ErrorResponse;
import cz.cvut.fel.vyzkumodolnosti.model.dto.forms.submitted.*;
import cz.cvut.fel.vyzkumodolnosti.services.forms.impl.SubmittedFormWriteServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping(value = "/form-submit")
public class FormSubmitController {
    private final SubmittedFormWriteServiceImpl writeService;

    @Autowired
    public FormSubmitController(SubmittedFormWriteServiceImpl writeService) {
        this.writeService = writeService;
    }

    @PostMapping()
    public void submit(@Valid @RequestBody FormInputDto dto) {
        log.info("Received new submitted form");

        if (dto.getPsqi() != null) {
            writeService.save(dto.getPsqi(), dto.getIdentifying());
        }
        if (dto.getMeq() != null) {
            writeService.save(dto.getMeq(), dto.getIdentifying());
        }
        if (dto.getDemo() != null) {
            writeService.save(dto.getDemo(), dto.getIdentifying());
        }
        if (dto.getMctq() != null) {
            writeService.save(dto.getMctq(), dto.getIdentifying());
        }
        if (dto.getPss() != null) {
            writeService.save(dto.getPss(), dto.getIdentifying());
        }
        if (dto.getDzs() != null) {
            writeService.save(dto.getDzs(), dto.getIdentifying());
        }
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
