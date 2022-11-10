package cz.cvut.fel.vyzkumodolnosti.model.entities.forms.submitted;

import cz.cvut.fel.vyzkumodolnosti.model.entities.AbstractEntity;
import cz.cvut.fel.vyzkumodolnosti.model.entities.forms.questions.Answer;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "submitted_form")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "form_type", discriminatorType = DiscriminatorType.STRING)
public class SubmittedForm extends AbstractEntity {
	@NotNull
	@Column(name = "created")
	private LocalDate created;
	@NotNull
	@Column(name = "respondent_identifier")
	private String respondentIdentifier;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "form", orphanRemoval = true)
	private List<Answer> answers;

	@PrePersist
	public void setCreatedPrePersist() {
		this.created = LocalDate.now();
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

	public List<Answer> getQuestionWithAnswers() {
		if (answers == null){
			answers = new ArrayList<>();
		}
		return answers;
	}

	public void setQuestionWithAnswers(List<Answer> answers) {
		this.answers = answers;
	}
}
