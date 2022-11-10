package cz.cvut.fel.vyzkumodolnosti.model.entities.forms.submitted;

import cz.cvut.fel.vyzkumodolnosti.model.entities.forms.evaluations.MctqEvaluation;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
@DiscriminatorValue("mctq")
public class MctqSubmittedForm extends SubmittedForm {
	@OneToOne(mappedBy = "submittedForm", cascade = CascadeType.ALL, orphanRemoval = true)
	private MctqEvaluation evaluation;

	public MctqEvaluation getEvaluation() {
		return evaluation;
	}

	public void setEvaluation(MctqEvaluation evaluation) {
		this.evaluation = evaluation;
	}
}
