package cz.cvut.fel.vyzkumodolnosti.services.computations;

import cz.cvut.fel.vyzkumodolnosti.model.dto.computations.UserComputationDataDto;
import cz.cvut.fel.vyzkumodolnosti.model.entities.ResearchParticipant;
import cz.cvut.fel.vyzkumodolnosti.model.entities.computations.UserComputationData;
import cz.cvut.fel.vyzkumodolnosti.repository.computations.UserComputationDataRepository;
import cz.cvut.fel.vyzkumodolnosti.repository.forms.ResearchParticipantRepository;
import cz.cvut.fel.vyzkumodolnosti.services.computations.mappers.UserComputationDataMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;

@Service
public class UserComputationDataService {

    private final static LocalTime sjlThreshold = LocalTime.of(1, 0);
    private final static int latencyFaThreshold = 30;

    @Autowired
    UserComputationDataRepository userComputationDataRepository;

    @Autowired
    ResearchParticipantRepository researchParticipantRepository;

    @Autowired
    ComputationUtilsService computationUtilsService;

    public UserComputationData getUserData(String researchNumber) {
        UserComputationData data = userComputationDataRepository.findByResearchParticipantResearchNumber(researchNumber);

        if(data == null) {

            ResearchParticipant researchParticipant = this.computationUtilsService.getResearchParticipantByResearchNumber(researchNumber);
            data = new UserComputationData(researchParticipant, sjlThreshold, latencyFaThreshold);
            userComputationDataRepository.save(data);
        }

        return data;
    }

    public UserComputationDataDto getUserDataDto(String id) {
        UserComputationData data = this.getUserData(id);
        return new UserComputationDataMapper().entityToDto(data);
    }

    public void updateUserComputationData(UserComputationData updated) {
        UserComputationData data =
                this.userComputationDataRepository.findByResearchParticipantResearchNumber(
                        updated.getResearchParticipant().getResearchNumber()
                );
        data.setLatencyFaThreshold(updated.getLatencyFaThreshold());
        data.setSocJetlagThreshold(updated.getSocJetlagThreshold());
        this.userComputationDataRepository.save(data);
    }
}
