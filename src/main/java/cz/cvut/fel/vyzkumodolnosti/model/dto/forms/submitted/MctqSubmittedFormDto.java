package cz.cvut.fel.vyzkumodolnosti.model.dto.forms.submitted;

import cz.cvut.fel.vyzkumodolnosti.model.dto.forms.evaluation.MctqEvaluationDto;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class MctqSubmittedFormDto extends SubmittedFormDto {
	@NotNull @Valid
	private MctqEvaluationDto evaluation;

	public MctqEvaluationDto getEvaluation() {
		return evaluation;
	}

	public void setEvaluation(MctqEvaluationDto evaluation) {
		this.evaluation = evaluation;
	}
}
