package cz.cvut.fel.vyzkumodolnosti.services.forms.api;

import cz.cvut.fel.vyzkumodolnosti.model.dto.forms.submitted.IdentifyingVariables;
import cz.cvut.fel.vyzkumodolnosti.model.dto.forms.submitted.SubmittedFormDto;
import cz.cvut.fel.vyzkumodolnosti.model.entities.forms.submitted.*;

public interface SubmittedFormMapper {
    SubmittedForm mapDtoToEntity(SubmittedFormDto dto, IdentifyingVariables identifyingVariables);
}
