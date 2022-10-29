package cz.cvut.fel.vyzkumodolnosti.model.entities.forms.submitted;

import cz.cvut.fel.vyzkumodolnosti.model.entities.forms.evaluations.PsqiEvaluation;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
@DiscriminatorValue("psqi")
public class PsqiSubmittedForm extends SubmittedForm {
	@OneToOne(mappedBy = "submittedForm", cascade = CascadeType.ALL, orphanRemoval = true)
	private PsqiEvaluation evaluation;

	public PsqiEvaluation getEvaluation() {
		return evaluation;
	}

	public void setEvaluation(PsqiEvaluation evaluation) {
		this.evaluation = evaluation;
	}
}
