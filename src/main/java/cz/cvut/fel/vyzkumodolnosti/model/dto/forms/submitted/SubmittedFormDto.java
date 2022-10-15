package cz.cvut.fel.vyzkumodolnosti.model.dto.forms.submitted;

import cz.cvut.fel.vyzkumodolnosti.model.dto.forms.questions.QuestionWithAnswerDto;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

public class SubmittedFormDto {
	@NotNull
	private String respondentIdentifier;
	@NotNull
	private List<QuestionWithAnswerDto> questions;

	public String getRespondentIdentifier() {
		return respondentIdentifier;
	}

	public void setRespondentIdentifier(String respondentIdentifier) {
		this.respondentIdentifier = respondentIdentifier;
	}

	public List<QuestionWithAnswerDto> getQuestions() {
		if (questions == null) {
			questions = new ArrayList<>();
		}
		return questions;
	}

	public void setQuestions(List<QuestionWithAnswerDto> questions) {
		this.questions = questions;
	}
}
