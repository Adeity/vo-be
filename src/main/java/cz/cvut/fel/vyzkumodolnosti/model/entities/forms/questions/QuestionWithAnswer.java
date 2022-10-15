package cz.cvut.fel.vyzkumodolnosti.model.entities.forms.questions;

import cz.cvut.fel.vyzkumodolnosti.model.domain.forms.QuestionTypeEnum;
import cz.cvut.fel.vyzkumodolnosti.model.entities.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "question_with_answer")
public class QuestionWithAnswer extends AbstractEntity {
	@Column
	@ManyToOne
	@JoinColumn(name = "question_definition_id", referencedColumnName = "code")
	private QuestionDefinition questionDefinition;

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name = "type", nullable = false)
	private QuestionTypeEnum type;

	@NotNull
	@Column(name = "answer", nullable = false)
	private String answer;

	@Column(name = "comment")
	private String comment;

	public QuestionDefinition getQuestionDefinition() {
		return questionDefinition;
	}

	public void setQuestionDefinition(QuestionDefinition questionDefinition) {
		this.questionDefinition = questionDefinition;
	}

	public QuestionTypeEnum getType() {
		return type;
	}

	public void setType(QuestionTypeEnum type) {
		this.type = type;
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
