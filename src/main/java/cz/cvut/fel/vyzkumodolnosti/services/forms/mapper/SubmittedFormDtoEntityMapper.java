package cz.cvut.fel.vyzkumodolnosti.services.forms.mapper;

import cz.cvut.fel.vyzkumodolnosti.model.dto.forms.questions.AnswerDto;
import cz.cvut.fel.vyzkumodolnosti.model.dto.forms.submitted.SubmittedFormDto;
import cz.cvut.fel.vyzkumodolnosti.model.entities.forms.questions.Answer;
import cz.cvut.fel.vyzkumodolnosti.model.entities.forms.submitted.SubmittedForm;

public class SubmittedFormDtoEntityMapper {
	public void mapSubmittedFormAttributes(SubmittedFormDto dto, SubmittedForm entity) {
		for (Answer i : entity.getQuestionWithAnswers()) {
			AnswerDto answerDto = AnswerMapper.mapEntityToDto(i);
			dto.getQuestions().add(answerDto);
		}
		dto.setRespondentIdentifier(entity.getRespondentIdentifier());
		dto.setId(entity.getId());
		dto.setCreated(entity.getCreated());
	}
}
