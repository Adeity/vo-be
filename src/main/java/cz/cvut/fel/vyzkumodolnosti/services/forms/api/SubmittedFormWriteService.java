package cz.cvut.fel.vyzkumodolnosti.services.forms.api;

import cz.cvut.fel.vyzkumodolnosti.model.dto.forms.submitted.*;

public interface SubmittedFormWriteService {
    void save(PsqiSubmittedFormDto dto, IdentifyingVariables identifyingVariables);

    void save(MctqSubmittedFormDto dto, IdentifyingVariables identifyingVariables);

    void save(MeqSubmittedFormDto dto, IdentifyingVariables identifyingVariables);

    void save(LifeSatisfactionSubmittedFormDto dto, IdentifyingVariables identifyingVariables);

    void save(DemoSubmittedFormDto dto, IdentifyingVariables identifyingVariables);

    void save(PssSubmittedFormDto dto, IdentifyingVariables identifyingVariables);
}
