package cz.cvut.fel.vyzkumodolnosti.model.dto.forms.submitted;

import cz.cvut.fel.vyzkumodolnosti.model.dto.forms.evaluation.LifeSatisfactionEvaluationDto;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class LifeSatisfactionSubmittedFormDto extends SubmittedFormDto {
	@NotNull @Valid
	private LifeSatisfactionEvaluationDto valuation;

	public LifeSatisfactionEvaluationDto getValuation() {
		return valuation;
	}

	public void setValuation(LifeSatisfactionEvaluationDto valuation) {
		this.valuation = valuation;
	}
}
