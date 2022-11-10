package cz.cvut.fel.vyzkumodolnosti.services.forms.mapper.evaluation;

import cz.cvut.fel.vyzkumodolnosti.model.dto.forms.evaluation.MctqEvaluationDto;
import cz.cvut.fel.vyzkumodolnosti.model.entities.forms.evaluations.MctqEvaluation;
import cz.cvut.fel.vyzkumodolnosti.model.entities.forms.submitted.MctqSubmittedForm;

public class MctqEvaluationMapper {

    private MctqEvaluationMapper() {
    }

    public static MctqEvaluation mapDtoToEntity(MctqEvaluationDto dto, MctqSubmittedForm submittedForm) {
        MctqEvaluation evaluation = new MctqEvaluation();

        evaluation.setSubmittedForm(submittedForm);

        return evaluation;
    }

    public static MctqEvaluationDto mapDtoToEntity(MctqEvaluation entity) {
        MctqEvaluationDto dto = new MctqEvaluationDto();


        return dto;
    }
}
