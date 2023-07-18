package cz.cvut.fel.vyzkumodolnosti.services.device.mappers;

import cz.cvut.fel.vyzkumodolnosti.model.dto.device.DeviceSleepEvaluationXlsDto;
import cz.cvut.fel.vyzkumodolnosti.model.entities.device.DeviceSleepEvaluation;
import cz.cvut.fel.vyzkumodolnosti.utils.TimeConversionUtils;
import org.springframework.stereotype.Service;

@Service
public class DeviceSleepEvaluationXlsMapper {

    public DeviceSleepEvaluationXlsDto entityToXls(DeviceSleepEvaluation entity) {
        DeviceSleepEvaluationXlsDto dto = new DeviceSleepEvaluationXlsDto();

        dto.setResearchNumber(entity.getResearchParticipant().getResearchNumber());
        dto.setAvgSleepWorkDays(secondsToHours(entity.getAvgSleepWorkDays()));
        dto.setAvgSleepFreeDays(secondsToHours(entity.getAvgSleepFreeDays()));
        dto.setAvgFallAsleepTimeWorkDays(entity.getAvgFallAsleepTimeWorkDays());
        dto.setAvgFallAsleepTimeFreeDays(entity.getAvgFallAsleepTimeFreeDays());
        dto.setAvgWakingTimeWorkDays(entity.getAvgWakingTimeWorkDays());
        dto.setAvgWakingTimeFreeDays(entity.getAvgWakingTimeFreeDays());
        dto.setExpectedSleepTimeWorkDays(TimeConversionUtils.secondsToLocalTime(entity.getExpectedSleepTimeWorkDays()));
        dto.setExpectedSleepTimeFreeDays(TimeConversionUtils.secondsToLocalTime(entity.getExpectedSleepTimeFreeDays()));
        dto.setCreated(entity.getCreated());

        return  dto;
    }

    private double secondsToHours(Double seconds) {
        return seconds / 3600;
    }
}
