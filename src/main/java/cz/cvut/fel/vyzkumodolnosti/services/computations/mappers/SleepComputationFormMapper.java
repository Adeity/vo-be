package cz.cvut.fel.vyzkumodolnosti.services.computations.mappers;

import cz.cvut.fel.vyzkumodolnosti.model.dto.computations.SleepComputationFormDto;
import cz.cvut.fel.vyzkumodolnosti.model.entities.ResearchParticipant;
import cz.cvut.fel.vyzkumodolnosti.model.entities.computations.SleepComputationForm;

public class SleepComputationFormMapper {

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

        return dto;
    }

    public SleepComputationForm dtoToEntity(SleepComputationFormDto dto) {

        SleepComputationForm entity = new SleepComputationForm();
        ResearchParticipant researchParticipant = new ResearchParticipant();
        researchParticipant.setResearchNumber(dto.getPerson_id());

        entity.setChronotype(dto.getChronotype());
        entity.setChronoFa(dto.getChronoFa());
        entity.setChronoWa(dto.getChronoWa());
        entity.setId(dto.getId());
        entity.setCreated(dto.getCreated());
        entity.setModified(dto.getModified());
        entity.setResearchParticipant(researchParticipant);
        entity.setLatencyFAGreater(dto.isLatencyFAGreater());
        entity.setSocJetlagGreater(dto.isSocJetlagGreater());
        entity.setChronoFaText(dto.getChronoFaText());
        entity.setChronoWaText(dto.getChronoWaText());
        entity.setLatencyFAGreaterText(dto.getLatencyFAGreaterText());
        entity.setSocJetlagGreaterText(dto.getSocJetlagGreaterText());
        entity.setAwakeFrom(dto.getAwakeFrom());
        entity.setAwakeTo(dto.getAwakeTo());
        entity.setSleepFrom(dto.getSleepFrom());
        entity.setSleepTo(dto.getSleepTo());


        return entity;
    }
}
