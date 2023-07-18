package cz.cvut.fel.vyzkumodolnosti.services.device.mappers;

import cz.cvut.fel.vyzkumodolnosti.handler.NoSuchResearchParticipantException;
import cz.cvut.fel.vyzkumodolnosti.model.dto.device.DeviceComputationFormDto;
import cz.cvut.fel.vyzkumodolnosti.model.entities.ResearchParticipant;
import cz.cvut.fel.vyzkumodolnosti.model.entities.device.DeviceComputationForm;
import cz.cvut.fel.vyzkumodolnosti.repository.forms.ResearchParticipantRepository;
import cz.cvut.fel.vyzkumodolnosti.services.computations.ComputationUtilsService;
import cz.cvut.fel.vyzkumodolnosti.utils.TimeConversionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.time.LocalTime;
import java.util.Optional;

@Service
public class DeviceComputationFormMapper {

    @Autowired
    ResearchParticipantRepository researchParticipantRepository;

    @Autowired
    ComputationUtilsService compUtilsService;

    private final String sqlInjectionRegex = "([';])+|(--)+";

    public DeviceComputationForm dtoToEntity(DeviceComputationFormDto dto) {

        DeviceComputationForm entity = new DeviceComputationForm();

        String researchNumber = dto.getResearchParticipantResearchNumber();

        ResearchParticipant researchParticipant = this.compUtilsService.getResearchParticipantByResearchNumber(researchNumber);

        entity.setId(dto.getId());
        entity.setResearchParticipant(researchParticipant);
        entity.setChronoFaWorkDays(dto.getChronoFaWorkDays());
        entity.setChronoWaWorkDays(dto.getChronoWaWorkDays());
        entity.setChronoFaFreeDays(dto.getChronoFaFreeDays());
        entity.setChronoWaFreeDays(dto.getChronoWaFreeDays());
        entity.setChronoFaTextWorkDays(dto.getChronoFaTextWorkDays().replaceAll(sqlInjectionRegex, ""));
        entity.setChronoWaTextWorkDays(dto.getChronoWaTextWorkDays().replaceAll(sqlInjectionRegex, ""));
        entity.setChronoFaTextFreeDays(dto.getChronoFaTextFreeDays().replaceAll(sqlInjectionRegex, ""));
        entity.setChronoWaTextFreeDays(dto.getChronoWaTextFreeDays().replaceAll(sqlInjectionRegex, ""));
        entity.setChronotype(dto.getChronotype());
        entity.setLatencyFAGreater(dto.isLatencyFAGreater());
        entity.setLatencyFAGreaterText(dto.getLatencyFAGreaterText().replaceAll(sqlInjectionRegex, ""));
        entity.setSocJetlagGreater(dto.isSocJetlagGreater());
        entity.setSocJetlagGreaterText(dto.getSocJetlagGreaterText().replaceAll(sqlInjectionRegex, ""));
        entity.setCompComparison(dto.getCompComparison());
        entity.setCreated(dto.getCreated());
        entity.setModified(dto.getModified());
        entity.setAwakeFrom(dto.getAwakeFrom());
        entity.setAwakeTo(dto.getAwakeTo());
        entity.setSleepFrom(dto.getSleepFrom());
        entity.setSleepTo(dto.getSleepTo());
        entity.setVersion(dto.getVersion());
        entity.setRecalculations(dto.getRecalculations());
        entity.setLatencyFaThreshold(dto.getLatencyFaThreshold());
        entity.setSocJetlagThreshold(TimeConversionUtils.hhMmToLocalTime(dto.getSocJetlagThreshold()));
        entity.setSocJetlag(dto.getSocJetlag());
        entity.setLatency(dto.getLatency());
        entity.setAvgFallAsleepTimeWorkDays(TimeConversionUtils.hhMmToLocalTime(dto.getAvgFallAsleepTimeWorkDays()));
        entity.setAvgWakingTimeWorkDays(TimeConversionUtils.hhMmToLocalTime(dto.getAvgWakingTimeWorkDays()));
        entity.setAvgFallAsleepTimeFreeDays(TimeConversionUtils.hhMmToLocalTime(dto.getAvgFallAsleepTimeFreeDays()));
        entity.setAvgWakingTimeFreeDays(TimeConversionUtils.hhMmToLocalTime(dto.getAvgWakingTimeFreeDays()));

        return entity;
    }

    public DeviceComputationFormDto entityToDto(DeviceComputationForm entity) {

        DeviceComputationFormDto dto = new DeviceComputationFormDto();

        dto.setId(entity.getId());
        dto.setResearchParticipantResearchNumber(entity.getResearchParticipant().getResearchNumber());
        dto.setChronoFaWorkDays(entity.getChronoFaWorkDays());
        dto.setChronoWaWorkDays(entity.getChronoWaWorkDays());
        dto.setChronoFaFreeDays(entity.getChronoFaFreeDays());
        dto.setChronoWaFreeDays(entity.getChronoWaFreeDays());
        dto.setChronoFaTextWorkDays(entity.getChronoFaTextWorkDays());
        dto.setChronoWaTextWorkDays(entity.getChronoWaTextWorkDays());
        dto.setChronoFaTextFreeDays(entity.getChronoFaTextFreeDays());
        dto.setChronoWaTextFreeDays(entity.getChronoWaTextFreeDays());
        dto.setChronotype(entity.getChronotype());
        dto.setLatencyFAGreater(entity.isLatencyFAGreater());
        dto.setLatencyFAGreaterText(entity.getLatencyFAGreaterText());
        dto.setSocJetlagGreater(entity.isSocJetlagGreater());
        dto.setSocJetlagGreaterText(entity.getSocJetlagGreaterText());
        dto.setCompComparison(entity.getCompComparison());
        dto.setCreated(entity.getCreated());
        dto.setModified(entity.getModified());
        dto.setAwakeFrom(entity.getAwakeFrom());
        dto.setAwakeTo(entity.getAwakeTo());
        dto.setSleepFrom(entity.getSleepFrom());
        dto.setSleepTo(entity.getSleepTo());
        dto.setVersion(entity.getVersion());
        dto.setRecalculations(entity.getRecalculations());
        dto.setLatencyFaThreshold(entity.getLatencyFaThreshold());
        dto.setSocJetlagThreshold(TimeConversionUtils.localTimeToHhMm(entity.getSocJetlagThreshold()));
        dto.setSocJetlag(entity.getSocJetlag());
        dto.setLatency(entity.getLatency());
        dto.setAvgFallAsleepTimeWorkDays(TimeConversionUtils.localTimeToHhMm(entity.getAvgFallAsleepTimeWorkDays()));
        dto.setAvgWakingTimeWorkDays(TimeConversionUtils.localTimeToHhMm(entity.getAvgWakingTimeWorkDays()));
        dto.setAvgFallAsleepTimeFreeDays(TimeConversionUtils.localTimeToHhMm(entity.getAvgFallAsleepTimeFreeDays()));
        dto.setAvgWakingTimeFreeDays(TimeConversionUtils.localTimeToHhMm(entity.getAvgWakingTimeFreeDays()));

        return dto;
    }
}
