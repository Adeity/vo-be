package cz.cvut.fel.vyzkumodolnosti.services.forms.mapper;

import cz.cvut.fel.vyzkumodolnosti.model.dto.forms.questions.QuestionWithAnswerDto;
import cz.cvut.fel.vyzkumodolnosti.model.entities.forms.questions.QuestionDefinition;
import cz.cvut.fel.vyzkumodolnosti.model.entities.forms.questions.QuestionWithAnswer;
import cz.cvut.fel.vyzkumodolnosti.model.entities.forms.submitted.SubmittedForm;

public class QuestionWithAnswerMapper {
	private QuestionWithAnswerMapper() {
	}

	public static QuestionWithAnswer mapDtoToEntity(QuestionWithAnswerDto dto, SubmittedForm form) {
		QuestionWithAnswer questionWithAnswer = new QuestionWithAnswer();

		QuestionDefinition definition = new QuestionDefinition();
		definition.setCode(dto.getQuestionDefinitionCode());
		questionWithAnswer.setQuestionDefinition(definition);

		questionWithAnswer.setType(dto.getType());
		questionWithAnswer.setAnswer(dto.getAnswer());
		questionWithAnswer.setComment(dto.getComment());
		questionWithAnswer.setForm(form);

		return questionWithAnswer;
	}
}
