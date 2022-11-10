package cz.cvut.fel.vyzkumodolnosti.services.forms.mapper.evaluation;

import cz.cvut.fel.vyzkumodolnosti.model.dto.forms.evaluation.PsqiEvaluationDto;
import cz.cvut.fel.vyzkumodolnosti.model.entities.forms.evaluations.PsqiEvaluation;
import cz.cvut.fel.vyzkumodolnosti.model.entities.forms.submitted.PsqiSubmittedForm;

public class PsqiEvaluationMapper {
	private PsqiEvaluationMapper() {
	}

	public static PsqiEvaluation mapDtoToEntity(PsqiEvaluationDto dto, PsqiSubmittedForm submittedForm) {
		PsqiEvaluation evaluation = new PsqiEvaluation();

		evaluation.setSubmittedForm(submittedForm);
		evaluation.setAverageLaydownTime(dto.getAverageLaydownTime());
		evaluation.setMinutesToFallAsleep(dto.getMinutesToFallAsleep());
		evaluation.setAverageTimeOfGettingUp(dto.getAverageTimeOfGettingUp());
		evaluation.setPsqidurat(dto.getPsqidurat());
		evaluation.setPsqidistb(dto.getPsqidistb());
		evaluation.setPsqilaten(dto.getPsqilaten());
		evaluation.setPsqidaydys(dto.getPsqidaydys());
		evaluation.setPsqihse(dto.getPsqihse());
		evaluation.setPsqislpqual(dto.getPsqislpqual());
		evaluation.setPsqimeds(dto.getPsqimeds());
		evaluation.setPsqitotal(dto.getPsqitotal());

		return evaluation;
	}

	public static PsqiEvaluationDto mapDtoToEntity(PsqiEvaluation entity) {
		PsqiEvaluationDto dto = new PsqiEvaluationDto();

		dto.setAverageLaydownTime(entity.getAverageLaydownTime());
		dto.setMinutesToFallAsleep(entity.getMinutesToFallAsleep());
		dto.setAverageTimeOfGettingUp(entity.getAverageTimeOfGettingUp());
		dto.setPsqidurat(entity.getPsqidurat());
		dto.setPsqidistb(entity.getPsqidistb());
		dto.setPsqilaten(entity.getPsqilaten());
		dto.setPsqidaydys(entity.getPsqidaydys());
		dto.setPsqihse(entity.getPsqihse());
		dto.setPsqislpqual(entity.getPsqislpqual());
		dto.setPsqimeds(entity.getPsqimeds());
		dto.setPsqitotal(entity.getPsqitotal());

		return dto;
	}
}
