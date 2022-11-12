package cz.cvut.fel.vyzkumodolnosti.services.forms.api;

import cz.cvut.fel.vyzkumodolnosti.model.dto.forms.submitted.SubmittedFormDto;
import cz.cvut.fel.vyzkumodolnosti.model.entities.forms.submitted.SubmittedForm;

public interface SubmittedFormMapper {
    SubmittedForm mapDtoToEntity(SubmittedFormDto dto);
}
