package cz.cvut.fel.vyzkumodolnosti.services.forms.xls;

import cz.cvut.fel.vyzkumodolnosti.model.dto.forms.evaluation.LifeSatisfactionEvaluationXlsDto;
import cz.cvut.fel.vyzkumodolnosti.model.dto.forms.evaluation.PssEvaluationXlsDto;
import cz.cvut.fel.vyzkumodolnosti.model.entities.forms.evaluations.LifeSatisfactionEvaluation;
import cz.cvut.fel.vyzkumodolnosti.model.entities.forms.evaluations.PssEvaluation;
import org.springframework.stereotype.Service;

@Service
public class LifeSatisfactionEvaluationXlsMapper {

    public LifeSatisfactionEvaluationXlsDto entityToXls(LifeSatisfactionEvaluation entity) {
        LifeSatisfactionEvaluationXlsDto dto = new LifeSatisfactionEvaluationXlsDto();

        dto.setZDR(entity.getZDR());
        dto.setPRZ(entity.getPRZ());
        dto.setFIN(entity.getFIN());
        dto.setVOL(entity.getVOL());
        dto.setMAN(entity.getMAN());
        dto.setDET(entity.getDET());
        dto.setVLO(entity.getVLO());
        dto.setSEX(entity.getSEX());
        dto.setPZP(entity.getPZP());
        dto.setBYD(entity.getBYD());
        dto.setSUM(entity.getSUM());
        dto.setHasKids(entity.getHasKids());
        dto.setHasPartner(entity.getHasPartner());

        dto.setLifeSatCreated(entity.getSubmittedForm().getCreated());

        return dto;
    }
}
