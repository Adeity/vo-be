package cz.cvut.fel.vyzkumodolnosti.model.entities.forms.submitted;

import cz.cvut.fel.vyzkumodolnosti.model.entities.AbstractEntity;
import cz.cvut.fel.vyzkumodolnosti.model.entities.forms.questions.QuestionWithAnswer;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "submitted_form")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "form_type", discriminatorType = DiscriminatorType.STRING)
public class SubmittedForm extends AbstractEntity {
	@NotNull
	@Column(name = "respondent_identifier")
	private String respondentIdentifier;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "form")
	private List<QuestionWithAnswer> questionWithAnswers;

	public String getRespondentIdentifier() {
		return respondentIdentifier;
	}

	public void setRespondentIdentifier(String respondentIdentifier) {
		this.respondentIdentifier = respondentIdentifier;
	}

	public List<QuestionWithAnswer> getQuestionWithAnswers() {
		if (questionWithAnswers == null){
			questionWithAnswers = new ArrayList<>();
		}
		return questionWithAnswers;
	}

	public void setQuestionWithAnswers(List<QuestionWithAnswer> questionWithAnswers) {
		this.questionWithAnswers = questionWithAnswers;
	}
}
