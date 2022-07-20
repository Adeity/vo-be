package cz.cvut.fel.pc2e.garminworker.model.mappers;

import cz.cvut.fel.pc2e.garminworker.model.dto.sleeps.SleepLevelTimeRangeDto;
import cz.cvut.fel.pc2e.garminworker.model.entities.sleeps.SleepLevelTimeRange;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SleepLevelTimeRangeToDtoMapper {
    SleepLevelTimeRangeToDtoMapper INSTANCE = Mappers.getMapper(SleepLevelTimeRangeToDtoMapper.class);

    SleepLevelTimeRangeDto sleepLevelTimeRangeToDto(SleepLevelTimeRange sleepLevelTimeRange);
}
