package cz.cvut.fel.vyzkumodolnosti.services.computations.mappers;

import cz.cvut.fel.vyzkumodolnosti.model.dto.computations.SingleGlobalValueDto;
import cz.cvut.fel.vyzkumodolnosti.model.entities.computations.GlobalChronotypeValue;
import cz.cvut.fel.vyzkumodolnosti.services.TimeComponent;

public class GlobalChronotypeValuesMapper {

    public GlobalChronotypeValue dtoToEntity(SingleGlobalValueDto dto) {

        TimeComponent tc = new TimeComponent();

        GlobalChronotypeValue entity = new GlobalChronotypeValue();

        entity.setTitle(dto.getTitle());
        entity.setAwakeFrom(tc.convertHhMmStringToLocalDate(dto.getAwakeFrom()));
        entity.setAwakeTo(tc.convertHhMmStringToLocalDate(dto.getAwakeTo()));
        entity.setSleepFrom(tc.convertHhMmStringToLocalDate(dto.getSleepFrom()));
        entity.setSleepTo(tc.convertHhMmStringToLocalDate(dto.getSleepTo()));
        entity.setId(dto.getId());

        return entity;
    }
}
