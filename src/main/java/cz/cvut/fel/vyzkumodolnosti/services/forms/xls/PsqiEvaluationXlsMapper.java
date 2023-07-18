package cz.cvut.fel.vyzkumodolnosti.services.forms.xls;

import cz.cvut.fel.vyzkumodolnosti.model.dto.forms.evaluation.PsqiEvaluationXlsDto;
import cz.cvut.fel.vyzkumodolnosti.model.entities.forms.evaluations.PsqiEvaluation;
import org.springframework.stereotype.Service;

@Service
public class PsqiEvaluationXlsMapper {

    public PsqiEvaluationXlsDto entityToXls(PsqiEvaluation entity) {

        PsqiEvaluationXlsDto dto = new PsqiEvaluationXlsDto();

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
        dto.setSleepDurationFreeDays(entity.getSleepDurationFreeDays());
        dto.setSleepDurationWorkDays(entity.getSleepDurationWorkDays());
        dto.setMidSleepFreeDays(entity.getMidSleepFreeDays());
        dto.setMidSleepWorkDays(entity.getMidSleepWorkDays());
        dto.setSJL(entity.getSJL());

        dto.setPsqiCreated(entity.getSubmittedForm().getCreated());

        return dto;
    }
}
