package cz.cvut.fel.vyzkumodolnosti.model.entities.forms.evaluations;

import cz.cvut.fel.vyzkumodolnosti.model.entities.AbstractEntity;
import cz.cvut.fel.vyzkumodolnosti.model.entities.forms.submitted.MeqSubmittedForm;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "meq_evaluation")
public class MeqEvaluation extends AbstractEntity {
	@NotNull
	@OneToOne
	@JoinColumn(name = "meq_submitted_form_id", referencedColumnName = "id")
	private MeqSubmittedForm submittedForm;

	@NotNull
	@Column(name = "meq_value")
	private Integer meqValue;

	public MeqSubmittedForm getSubmittedForm() {
		return submittedForm;
	}

	public void setSubmittedForm(MeqSubmittedForm submittedForm) {
		this.submittedForm = submittedForm;
	}

	public Integer getMeqValue() {
		return meqValue;
	}

	public void setMeqValue(Integer meqValue) {
		this.meqValue = meqValue;
	}
}
