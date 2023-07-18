package cz.cvut.fel.vyzkumodolnosti.services.computations.mappers;

import cz.cvut.fel.vyzkumodolnosti.model.dto.computations.UserComputationDataDto;
import cz.cvut.fel.vyzkumodolnosti.model.entities.ResearchParticipant;
import cz.cvut.fel.vyzkumodolnosti.model.entities.computations.UserComputationData;
import cz.cvut.fel.vyzkumodolnosti.repository.forms.ResearchParticipantRepository;
import cz.cvut.fel.vyzkumodolnosti.services.computations.ComputationUtilsService;
import cz.cvut.fel.vyzkumodolnosti.utils.TimeConversionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;

@Service
public class UserComputationDataMapper {


    @Autowired
    ResearchParticipantRepository researchParticipantRepository;

    @Autowired
    ComputationUtilsService compUtilsService;

    public UserComputationDataDto entityToDto(UserComputationData entity) {
        UserComputationDataDto dto = new UserComputationDataDto();
        dto.setResearchNumber(entity.getResearchParticipant().getResearchNumber());
        dto.setLatencyFaThreshold(entity.getLatencyFaThreshold());
        dto.setSocJetlagThreshold(TimeConversionUtils.localTimeToHhMm(entity.getSocJetlagThreshold()));

        return dto;
    }

    public UserComputationData dtoToEntity(UserComputationDataDto dto) {

        ResearchParticipant researchParticipant = this.compUtilsService.getResearchParticipantByResearchNumber(dto.getResearchNumber());

        LocalTime sjlThreshold = TimeConversionUtils.hhMmToLocalTime(dto.getSocJetlagThreshold());
        return new UserComputationData(researchParticipant, sjlThreshold, dto.getLatencyFaThreshold());
    }
}
