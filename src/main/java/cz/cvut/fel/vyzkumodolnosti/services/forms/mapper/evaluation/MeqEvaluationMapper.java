package cz.cvut.fel.vyzkumodolnosti.services.forms.mapper.evaluation;

import cz.cvut.fel.vyzkumodolnosti.model.dto.forms.evaluation.MeqEvaluationDto;
import cz.cvut.fel.vyzkumodolnosti.model.entities.forms.evaluations.MeqEvaluation;
import cz.cvut.fel.vyzkumodolnosti.model.entities.forms.submitted.MeqSubmittedForm;

public class MeqEvaluationMapper {
    private MeqEvaluationMapper() {
    }

    public static MeqEvaluation mapDtoToEntity(MeqEvaluationDto dto, MeqSubmittedForm submittedForm) {
        MeqEvaluation evaluation = new MeqEvaluation();

        evaluation.setSubmittedForm(submittedForm);

        return evaluation;
    }

    public static MeqEvaluationDto mapDtoToEntity(MeqEvaluation entity) {
        MeqEvaluationDto dto = new MeqEvaluationDto();


        return dto;
    }

}
