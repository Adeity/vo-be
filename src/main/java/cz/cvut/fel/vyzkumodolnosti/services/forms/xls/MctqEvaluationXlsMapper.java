package cz.cvut.fel.vyzkumodolnosti.services.forms.xls;

import cz.cvut.fel.vyzkumodolnosti.model.dto.forms.evaluation.MctqEvaluationXlsDto;
import cz.cvut.fel.vyzkumodolnosti.model.entities.forms.evaluations.MctqEvaluation;
import org.springframework.stereotype.Service;

@Service
public class MctqEvaluationXlsMapper {

    public MctqEvaluationXlsDto entityToXls(MctqEvaluation entity) {

        MctqEvaluationXlsDto dto = new MctqEvaluationXlsDto();

        dto.setSOw(entity.getSOw());
        dto.setGUw(entity.getGUw());
        dto.setSDw(entity.getSDw());
        dto.setTBTw(entity.getTBTw());
        dto.setMSW(entity.getMSW());
        dto.setSOf(entity.getSOf());
        dto.setGUf(entity.getGUf());
        dto.setSDf(entity.getSDf());
        dto.setTBTf(entity.getTBTf());
        dto.setMSF(entity.getMSF());
        dto.setSDweek(entity.getSDweek());
        dto.setMSFsc(entity.getMSFsc());
        dto.setSLossweek(entity.getSLossweek());
        dto.setSJLrel(entity.getSJLrel());
        dto.setSJL(entity.getSJL());
        dto.setLEweek(entity.getLEweek());

        dto.setMctqCreated(entity.getSubmittedForm().getCreated());

        return dto;
    }
}
