package cz.cvut.fel.vyzkumodolnosti.services.forms.mapper;

import cz.cvut.fel.vyzkumodolnosti.model.dto.forms.questions.AnswerDto;
import cz.cvut.fel.vyzkumodolnosti.model.entities.forms.questions.Question;
import cz.cvut.fel.vyzkumodolnosti.model.entities.forms.questions.Answer;
import cz.cvut.fel.vyzkumodolnosti.model.entities.forms.submitted.SubmittedForm;

public class AnswerMapper {
	private AnswerMapper() {
	}

	public static Answer mapDtoToEntity(AnswerDto dto, SubmittedForm form) {
		Answer answer = new Answer();

		Question definition = new Question();
		definition.setCode(dto.getQuestionCode());
		answer.setQuestion(definition);

		answer.setValue(dto.getAnswer());
		answer.setNote(dto.getComment());
		answer.setForm(form);

		return answer;
	}

	public static AnswerDto mapEntityToDto(Answer entity) {
		AnswerDto dto = new AnswerDto();
		dto.setQuestionCode(entity.getQuestion().getCode());
		dto.setAnswer(entity.getValue());
		dto.setComment(entity.getNote());

		return dto;
	}
}
