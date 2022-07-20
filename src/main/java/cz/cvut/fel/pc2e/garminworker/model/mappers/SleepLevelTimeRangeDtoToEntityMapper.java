package cz.cvut.fel.pc2e.garminworker.model.mappers;

import cz.cvut.fel.pc2e.garminworker.model.dto.sleeps.SleepLevelTimeRangeDto;
import cz.cvut.fel.pc2e.garminworker.model.entities.sleeps.SleepLevelTimeRange;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;


@Mapper
public interface SleepLevelTimeRangeDtoToEntityMapper {
    SleepLevelTimeRangeDtoToEntityMapper INSTANCE = Mappers.getMapper(SleepLevelTimeRangeDtoToEntityMapper.class);

    SleepLevelTimeRange sleepToXlsSleepDto(SleepLevelTimeRangeDto sleepLevelTimeRange);
}
