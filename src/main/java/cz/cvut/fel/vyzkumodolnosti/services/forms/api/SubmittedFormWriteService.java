package cz.cvut.fel.vyzkumodolnosti.services.forms.api;

import cz.cvut.fel.vyzkumodolnosti.model.dto.forms.submitted.LifeSatisfactionSubmittedFormDto;
import cz.cvut.fel.vyzkumodolnosti.model.dto.forms.submitted.MctqSubmittedFormDto;
import cz.cvut.fel.vyzkumodolnosti.model.dto.forms.submitted.MeqSubmittedFormDto;
import cz.cvut.fel.vyzkumodolnosti.model.dto.forms.submitted.PsqiSubmittedFormDto;

public interface SubmittedFormWriteService {
    void save(PsqiSubmittedFormDto dto);

    void save(MctqSubmittedFormDto dto);

    void save(MeqSubmittedFormDto dto);

    void save(LifeSatisfactionSubmittedFormDto dto);
}
