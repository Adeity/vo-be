package cz.cvut.fel.vyzkumodolnosti.model.dto.forms.submitted;

import cz.cvut.fel.vyzkumodolnosti.model.dto.forms.evaluation.PsqiEvaluationDto;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class PsqiSubmittedFormDto extends SubmittedFormDto {
	@NotNull @Valid
	private PsqiEvaluationDto evaluation;

	public PsqiEvaluationDto getEvaluation() {
		return evaluation;
	}

	public void setEvaluation(PsqiEvaluationDto evaluation) {
		this.evaluation = evaluation;
	}
}
