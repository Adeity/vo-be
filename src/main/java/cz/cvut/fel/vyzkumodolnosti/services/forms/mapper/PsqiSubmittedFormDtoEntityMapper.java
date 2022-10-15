package cz.cvut.fel.vyzkumodolnosti.services.forms.mapper;

import cz.cvut.fel.vyzkumodolnosti.model.dto.forms.submitted.PsqiSubmittedFormDto;
import cz.cvut.fel.vyzkumodolnosti.model.entities.forms.submitted.PsqiSubmittedForm;

public class PsqiSubmittedFormDtoEntityMapper {
	private PsqiSubmittedFormDtoEntityMapper() {
	}

	public static PsqiSubmittedForm mapDtoToEntity(PsqiSubmittedFormDto dto) {
		PsqiSubmittedForm form = new PsqiSubmittedForm();
		form.setEvaluation(PsqiEvaluationMapper.mapDtoToEntity(dto.getEvaluation(), form));
		form.setRespondentIdentifier(dto.getRespondentIdentifier());
		for (var i : dto.getQuestions()) {
			form.getQuestionWithAnswers().add(QuestionWithAnswerMapper.mapDtoToEntity(i, form));
		}

		return form;
	}

	public static PsqiSubmittedFormDto mapEntityToDto(PsqiSubmittedForm entity) {
		return null;
	}
}
