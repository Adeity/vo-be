package cz.cvut.fel.vyzkumodolnosti.model.dto.forms.submitted;

import cz.cvut.fel.vyzkumodolnosti.model.dto.forms.evaluation.MeqEvaluationDto;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class MeqSubmittedFormDto extends SubmittedFormDto {
	@NotNull @Valid
	private MeqEvaluationDto evaluation;

	public MeqEvaluationDto getEvaluation() {
		return evaluation;
	}

	public void setEvaluation(MeqEvaluationDto evaluation) {
		this.evaluation = evaluation;
	}
}
