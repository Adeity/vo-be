package cz.cvut.fel.vyzkumodolnosti.model.entities.forms.questions;

import cz.cvut.fel.vyzkumodolnosti.model.entities.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "question_definition", indexes = @Index(columnList = "code"))
public class QuestionDefinition extends AbstractEntity {
	@NotNull
	@Column(name = "code", columnDefinition = "text", unique = true)
	private String code;
	@NotNull
	@Column(name = "text", columnDefinition = "text")
	private String text;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
