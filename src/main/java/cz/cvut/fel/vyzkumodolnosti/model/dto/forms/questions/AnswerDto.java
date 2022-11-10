package cz.cvut.fel.vyzkumodolnosti.model.dto.forms.questions;

import cz.cvut.fel.vyzkumodolnosti.model.domain.forms.QuestionTypeEnum;

import javax.validation.constraints.NotNull;

public class AnswerDto {
	@NotNull
	private String questionCode;
	@NotNull
	private String answer;
	private String comment;

	public String getQuestionCode() {
		return questionCode;
	}

	public void setQuestionCode(String questionCode) {
		this.questionCode = questionCode;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
}
