package cz.cvut.fel.vyzkumodolnosti.services.forms.mapper.evaluation;

import cz.cvut.fel.vyzkumodolnosti.model.dto.forms.evaluation.LifeSatisfactionEvaluationDto;
import cz.cvut.fel.vyzkumodolnosti.model.entities.forms.evaluations.LifeSatisfactionEvaluation;
import cz.cvut.fel.vyzkumodolnosti.model.entities.forms.submitted.LifeSatisfactionSubmittedForm;

public class LifeSatisfactionEvaluationMapper {
    private LifeSatisfactionEvaluationMapper() {
    }

    public static LifeSatisfactionEvaluation mapDtoToEntity(LifeSatisfactionEvaluationDto dto, LifeSatisfactionSubmittedForm submittedForm) {
        LifeSatisfactionEvaluation evaluation = new LifeSatisfactionEvaluation();

        evaluation.setSubmittedForm(submittedForm);

        return evaluation;
    }

    public static LifeSatisfactionEvaluationDto mapDtoToEntity(LifeSatisfactionEvaluation entity) {
        LifeSatisfactionEvaluationDto dto = new LifeSatisfactionEvaluationDto();


        return dto;
    }

}
