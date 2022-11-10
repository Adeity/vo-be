package cz.cvut.fel.vyzkumodolnosti.services.forms.mapper;

import cz.cvut.fel.vyzkumodolnosti.model.dto.forms.submitted.PsqiSubmittedFormDto;
import cz.cvut.fel.vyzkumodolnosti.model.entities.forms.submitted.PsqiSubmittedForm;
import cz.cvut.fel.vyzkumodolnosti.services.forms.mapper.evaluation.PsqiEvaluationMapper;

public class PsqiSubmittedFormDtoEntityMapper extends SubmittedFormDtoEntityMapper{
	private PsqiSubmittedFormDtoEntityMapper() {
	}

	public static PsqiSubmittedForm mapDtoToEntity(PsqiSubmittedFormDto dto) {
		PsqiSubmittedForm form = new PsqiSubmittedForm();
		form.setEvaluation(PsqiEvaluationMapper.mapDtoToEntity(dto.getEvaluation(), form));
		form.setRespondentIdentifier(dto.getRespondentIdentifier());
		for (var i : dto.getQuestions()) {
			form.getQuestionWithAnswers().add(AnswerMapper.mapDtoToEntity(i, form));
		}

		return form;
	}

	public static PsqiSubmittedFormDto mapEntityToDto(PsqiSubmittedForm entity) {
		SubmittedFormDtoEntityMapper mapper = new SubmittedFormDtoEntityMapper();
		PsqiSubmittedFormDto dto = new PsqiSubmittedFormDto();
		mapper.mapSubmittedFormAttributes(dto, entity);
		dto.setEvaluation(PsqiEvaluationMapper.mapDtoToEntity(entity.getEvaluation()));
		return dto;
	}
}
