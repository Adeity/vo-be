package cz.cvut.fel.pc2e.garminworker.model.mappers;

import cz.cvut.fel.pc2e.garminworker.model.dto.sleeps.SleepSummaryLiteDto;
import cz.cvut.fel.pc2e.garminworker.model.entities.sleeps.SleepSummary;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SleepSummaryMapper {
    SleepSummaryMapper INSTANCE = Mappers.getMapper(SleepSummaryMapper.class);

    SleepSummaryLiteDto sleepToXlsSleepDto(SleepSummary sleepSummary);
}
