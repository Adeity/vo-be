package cz.cvut.fel.vyzkumodolnosti.services.forms.xls;

import cz.cvut.fel.vyzkumodolnosti.model.dto.forms.evaluation.MeqEvaluationXlsDto;
import cz.cvut.fel.vyzkumodolnosti.model.entities.forms.evaluations.MeqEvaluation;
import org.springframework.stereotype.Service;

@Service
public class MeqEvaluationXlsMapper {

    public MeqEvaluationXlsDto entityToXls(MeqEvaluation entity) {

        MeqEvaluationXlsDto dto = new MeqEvaluationXlsDto();

        dto.setMeqValue(entity.getMeqValue());
        dto.setMeqCreated(entity.getSubmittedForm().getCreated());

        return dto;
    }
}
