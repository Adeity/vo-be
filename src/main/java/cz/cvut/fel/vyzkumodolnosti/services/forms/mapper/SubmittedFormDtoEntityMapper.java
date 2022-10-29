package cz.cvut.fel.vyzkumodolnosti.services.forms.mapper;

import cz.cvut.fel.vyzkumodolnosti.model.dto.forms.questions.QuestionWithAnswerDto;
import cz.cvut.fel.vyzkumodolnosti.model.dto.forms.submitted.SubmittedFormDto;
import cz.cvut.fel.vyzkumodolnosti.model.entities.forms.questions.QuestionWithAnswer;
import cz.cvut.fel.vyzkumodolnosti.model.entities.forms.submitted.SubmittedForm;

public class SubmittedFormDtoEntityMapper {
	public void mapSubmittedFormAttributes(SubmittedFormDto dto, SubmittedForm entity) {
		for (QuestionWithAnswer i : entity.getQuestionWithAnswers()) {
			QuestionWithAnswerDto questionWithAnswerDto = QuestionWithAnswerMapper.mapEntityToDto(i);
			dto.getQuestions().add(questionWithAnswerDto);
		}
		dto.setRespondentIdentifier(entity.getRespondentIdentifier());
		dto.setId(entity.getId());
		dto.setCreated(entity.getCreated());
	}
}
