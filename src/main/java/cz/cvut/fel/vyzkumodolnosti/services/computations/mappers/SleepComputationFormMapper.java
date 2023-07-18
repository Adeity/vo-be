package cz.cvut.fel.vyzkumodolnosti.services.computations.mappers;

import cz.cvut.fel.vyzkumodolnosti.model.dto.computations.SleepComputationFormDto;
import cz.cvut.fel.vyzkumodolnosti.model.entities.ResearchParticipant;
import cz.cvut.fel.vyzkumodolnosti.model.entities.computations.SleepComputationForm;
import cz.cvut.fel.vyzkumodolnosti.services.computations.ComputationUtilsService;
import cz.cvut.fel.vyzkumodolnosti.utils.TimeConversionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;

@Service
public class SleepComputationFormMapper {

    @Autowired
    ComputationUtilsService computationUtilsService;

    private final String sqlInjectionRegex = "([';])+|(--)+";

    public SleepComputationFormDto entityToDto(SleepComputationForm entity) {

        SleepComputationFormDto dto = new SleepComputationFormDto();

        dto.setChronotype(entity.getChronotype());
        dto.setChronoFa(entity.getChronoFa());
        dto.setChronoWa(entity.getChronoWa());
        dto.setId(entity.getId());
        dto.setCreated(entity.getCreated());
        dto.setModified(entity.getModified());
        dto.setPerson_id(entity.getPersonId());
        dto.setLatencyFAGreater(entity.isLatencyFAGreater());
        dto.setSocJetlagGreater(entity.isSocJetlagGreater());
        dto.setChronoFaText(entity.getChronoFaText());
        dto.setChronoWaText(entity.getChronoWaText());
        dto.setLatencyFAGreaterText(entity.getLatencyFAGreaterText());
        dto.setSocJetlagGreaterText(entity.getSocJetlagGreaterText());
        dto.setAwakeFrom(entity.getAwakeFrom());
        dto.setAwakeTo(entity.getAwakeTo());
        dto.setSleepFrom(entity.getSleepFrom());
        dto.setSleepTo(entity.getSleepTo());
        dto.setVersion(entity.getVersion());
        dto.setRecalculations(entity.getRecalculations());
        dto.setLatency(entity.getLatency());
        dto.setSocJetlag(entity.getSocJetlag());
        dto.setLatencyFaThreshold(entity.getLatencyFaThreshold());
        dto.setSocJetlagThreshold(TimeConversionUtils.localTimeToHhMm(entity.getSocJetlagThreshold()));
        dto.setAvgLaydownTime(entity.getAvgLaydownTime());
        dto.setAvgWakingTime(entity.getAvgWakingTime());

        return dto;
    }

    public SleepComputationForm dtoToEntity(SleepComputationFormDto dto) {

        SleepComputationForm entity = new SleepComputationForm();
        ResearchParticipant researchParticipant =
                this.computationUtilsService.getResearchParticipantByResearchNumber(dto.getPerson_id());

        entity.setResearchParticipant(researchParticipant);
        entity.setChronotype(dto.getChronotype());
        entity.setChronoFa(dto.getChronoFa());
        entity.setChronoWa(dto.getChronoWa());
        entity.setId(dto.getId());
        entity.setCreated(dto.getCreated());
        entity.setModified(dto.getModified());
        entity.setLatencyFAGreater(dto.isLatencyFAGreater());
        entity.setSocJetlagGreater(dto.isSocJetlagGreater());
        entity.setChronoFaText(dto.getChronoFaText().replaceAll(sqlInjectionRegex, ""));
        entity.setChronoWaText(dto.getChronoWaText().replaceAll(sqlInjectionRegex, ""));
        entity.setLatencyFAGreaterText(dto.getLatencyFAGreaterText().replaceAll(sqlInjectionRegex, ""));
        entity.setSocJetlagGreaterText(dto.getSocJetlagGreaterText().replaceAll(sqlInjectionRegex, ""));
        entity.setAwakeFrom(dto.getAwakeFrom());
        entity.setAwakeTo(dto.getAwakeTo());
        entity.setSleepFrom(dto.getSleepFrom());
        entity.setSleepTo(dto.getSleepTo());
        entity.setVersion(dto.getVersion());
        entity.setRecalculations(dto.getRecalculations());
        entity.setLatency(dto.getLatency());
        entity.setSocJetlag(dto.getSocJetlag());
        entity.setLatencyFaThreshold(dto.getLatencyFaThreshold());
        entity.setSocJetlagThreshold(TimeConversionUtils.hhMmToLocalTime(dto.getSocJetlagThreshold()));
        entity.setAvgLaydownTime(dto.getAvgLaydownTime());
        entity.setAvgWakingTime(dto.getAvgWakingTime());

        return entity;
    }
}
