package cz.cvut.fel.vyzkumodolnosti.model.dto.forms.submitted;

import cz.cvut.fel.vyzkumodolnosti.model.dto.forms.questions.AnswerDto;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SubmittedFormDto {
	private int id;
	private LocalDate created;
	@NotNull
	private String respondentIdentifier;
	@NotNull
	private List<@Valid AnswerDto> questions;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDate getCreated() {
		return created;
	}

	public void setCreated(LocalDate created) {
		this.created = created;
	}

	public String getRespondentIdentifier() {
		return respondentIdentifier;
	}

	public void setRespondentIdentifier(String respondentIdentifier) {
		this.respondentIdentifier = respondentIdentifier;
	}

	public List<AnswerDto> getQuestions() {
		if (questions == null) {
			questions = new ArrayList<>();
		}
		return questions;
	}

	public void setQuestions(List<AnswerDto> questions) {
		this.questions = questions;
	}
}
