package cz.cvut.fel.vyzkumodolnosti.model.dto.forms.evaluation;

import javax.validation.constraints.NotNull;

public class PsqiEvaluationDto {
	@NotNull
	private String averageLaydownTime;
	@NotNull
	private Integer minutesToFallAsleep;
	@NotNull
	private String averageTimeOfGettingUp;
	@NotNull
	private Integer psqidurat;
	@NotNull
	private Integer psqidistb;
	@NotNull
	private Integer psqilaten;
	@NotNull
	private Integer psqidaydys;
	@NotNull
	private Integer psqihse;
	@NotNull
	private Integer psqislpqual;
	@NotNull
	private Integer psqimeds;
	@NotNull
	private Integer psqitotal;
}
