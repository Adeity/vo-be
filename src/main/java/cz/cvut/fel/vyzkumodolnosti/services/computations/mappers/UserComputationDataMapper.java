package cz.cvut.fel.vyzkumodolnosti.services.computations.mappers;

import cz.cvut.fel.vyzkumodolnosti.model.dto.computations.UpdateUserDataDto;
import cz.cvut.fel.vyzkumodolnosti.model.entities.computations.UserComputationData;

import java.time.LocalTime;

public class UserComputationDataMapper {

    public UpdateUserDataDto entityToDto(UserComputationData entity) {
        UpdateUserDataDto dto = new UpdateUserDataDto();
        dto.setUserId(entity.getId());
        dto.setLatencyFaThreshold(entity.getLatencyFaThreshold());

        String hours = String.format("%02d", entity.getSocJetlagThreshold().getHour());
        String minutes = String.format("%02d", entity.getSocJetlagThreshold().getMinute());

        dto.setSocJetlagThreshold(hours + ":" + minutes);

        return dto;
    }

    public UserComputationData dtoToEntity(UpdateUserDataDto dto) {

        int hours = Integer.parseInt(dto.getSocJetlagThreshold().substring(0, 2));
        int minutes = Integer.parseInt(dto.getSocJetlagThreshold().substring(3, 4));

        LocalTime sjlThreshold = LocalTime.of(hours, minutes);
        UserComputationData entity = new UserComputationData(dto.getUserId(), sjlThreshold, dto.getLatencyFaThreshold());
        return entity;
    }
}
