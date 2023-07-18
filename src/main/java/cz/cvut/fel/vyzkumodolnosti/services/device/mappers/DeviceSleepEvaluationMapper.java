package cz.cvut.fel.vyzkumodolnosti.services.device.mappers;

import cz.cvut.fel.vyzkumodolnosti.model.dto.device.DeviceSleepEvaluationDto;
import cz.cvut.fel.vyzkumodolnosti.model.entities.ResearchParticipant;
import cz.cvut.fel.vyzkumodolnosti.model.entities.device.DeviceSleepEvaluation;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
@Service
public class DeviceSleepEvaluationMapper {

    public DeviceSleepEvaluationDto entityToDto(DeviceSleepEvaluation entity) {

        DeviceSleepEvaluationDto dto = new DeviceSleepEvaluationDto();

        dto.setId(entity.getResearchParticipant().getResearchNumber());
        dto.setAvgSleepWorkDays(entity.getAvgSleepWorkDays());
        dto.setAvgSleepFreeDays(entity.getAvgSleepFreeDays());
        dto.setAvgFallAsleepTimeWorkDays(entity.getAvgFallAsleepTimeWorkDays());
        dto.setAvgFallAsleepTimeFreeDays(entity.getAvgFallAsleepTimeFreeDays());
        dto.setAvgWakingTimeWorkDays(entity.getAvgWakingTimeWorkDays());
        dto.setAvgWakingTimeFreeDays(entity.getAvgWakingTimeFreeDays());
        dto.setExpectedSleepTimeWorkDays(entity.getExpectedSleepTimeWorkDays());
        dto.setExpectedSleepTimeFreeDays(entity.getExpectedSleepTimeFreeDays());
        dto.setCreated(entity.getCreated());
        dto.setVersion(entity.getVersion());

        dto.setSocJetlag(this.getSocJetlag(entity.getExpectedSleepTimeFreeDays(), entity.getExpectedSleepTimeWorkDays()));
        dto.setAvgFallAsleepTimeWeek(this.getAvgFallAsleepTimeWeek(entity.getAvgFallAsleepTimeWorkDays(), entity.getAvgFallAsleepTimeFreeDays()));
        dto.setAvgWakingTimeWeek(this.getAvgWakingTimeWeek(entity.getAvgWakingTimeWorkDays(), entity.getAvgWakingTimeFreeDays()));
        dto.setAvgSleepWeek(this.getAvgSleepWeek(entity.getAvgSleepWorkDays(), entity.getAvgSleepFreeDays()));

        return dto;
    }


    public DeviceSleepEvaluation dtoToEntity(DeviceSleepEvaluationDto dto) {
        DeviceSleepEvaluation entity = new DeviceSleepEvaluation();
        ResearchParticipant rp = new ResearchParticipant();
        rp.setResearchNumber(dto.getId());

        entity.setResearchParticipant(rp);
        entity.setAvgSleepWorkDays(dto.getAvgSleepWorkDays());
        entity.setAvgSleepFreeDays(dto.getAvgSleepFreeDays());
        entity.setAvgFallAsleepTimeWorkDays(dto.getAvgFallAsleepTimeWorkDays());
        entity.setAvgFallAsleepTimeFreeDays(dto.getAvgFallAsleepTimeFreeDays());
        entity.setAvgWakingTimeWorkDays(dto.getAvgWakingTimeWorkDays());
        entity.setAvgWakingTimeFreeDays(dto.getAvgWakingTimeFreeDays());
        entity.setExpectedSleepTimeWorkDays(dto.getExpectedSleepTimeWorkDays());
        entity.setExpectedSleepTimeFreeDays(dto.getExpectedSleepTimeFreeDays());
        entity.setCreated(dto.getCreated());
        entity.setVersion(dto.getVersion());

        return entity;
    }




    public double getSocJetlag(double expectedSleepTimeFreeDays, double expectedSleepTimeWorkDays) {
        return Math.abs(expectedSleepTimeFreeDays - expectedSleepTimeWorkDays);
    }

    public LocalTime getAvgFallAsleepTimeWeek(LocalTime avgFallAsleepTimeWorkDays, LocalTime avgFallAsleepTimeFreeDays) {
        return weightedAverageTime(avgFallAsleepTimeWorkDays, avgFallAsleepTimeFreeDays);
    }

    public LocalTime getAvgWakingTimeWeek(LocalTime avgWakingTimeWorkDays, LocalTime avgWakingTimeFreeDays) {
        return weightedAverageTime(avgWakingTimeWorkDays, avgWakingTimeFreeDays);

    }

    public double getAvgSleepWeek(double avgSleepWorkDays, double avgSleepFreeDays) {
        return (5 * avgSleepWorkDays + 2 * avgSleepFreeDays) / 7;
    }

    private LocalTime weightedAverageTime(LocalTime weekdaysAvg, LocalTime freeDaysAvg) {
        double seconds = (5 * (double) weekdaysAvg.toSecondOfDay() + 2* (double) freeDaysAvg.toSecondOfDay()) / 7;
        return LocalTime.ofSecondOfDay((long) seconds);
    }
}
