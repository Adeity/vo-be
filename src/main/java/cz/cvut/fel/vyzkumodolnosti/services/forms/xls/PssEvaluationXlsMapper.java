package cz.cvut.fel.vyzkumodolnosti.services.forms.xls;

import cz.cvut.fel.vyzkumodolnosti.model.dto.forms.evaluation.PssEvaluationXlsDto;
import cz.cvut.fel.vyzkumodolnosti.model.entities.forms.evaluations.PssEvaluation;
import org.springframework.stereotype.Service;

@Service
public class PssEvaluationXlsMapper {

    public PssEvaluationXlsDto entityToXls(PssEvaluation entity) {
        PssEvaluationXlsDto dto = new PssEvaluationXlsDto();

        dto.setPssSum(entity.getPssSum());
        dto.setPssPos(entity.getPssPos());
        dto.setPssNeg(entity.getPssNeg());
        dto.setPssCreated(entity.getSubmittedForm().getCreated());

        return dto;
    }
}
