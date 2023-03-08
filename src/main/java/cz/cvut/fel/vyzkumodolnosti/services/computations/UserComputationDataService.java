package cz.cvut.fel.vyzkumodolnosti.services.computations;

import cz.cvut.fel.vyzkumodolnosti.model.dto.computations.UpdateUserDataDto;
import cz.cvut.fel.vyzkumodolnosti.model.entities.computations.UserComputationData;
import cz.cvut.fel.vyzkumodolnosti.repository.computations.UserComputationDataRepository;
import cz.cvut.fel.vyzkumodolnosti.services.computations.mappers.UserComputationDataMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;

@Service
public class UserComputationDataService {

    private final static LocalTime sjlThreshold = LocalTime.of(1, 0);
    private final static int latencyFaThreshold = 30;

    @Autowired
    UserComputationDataRepository repository;

    public UserComputationData getUserData(String id) {
        UserComputationData data = repository.findByUserId(id);

        if(data == null) {
            data = new UserComputationData(id, sjlThreshold, latencyFaThreshold);
            repository.save(new UserComputationData(id, sjlThreshold, latencyFaThreshold));
        }

        return data;
    }

    public UpdateUserDataDto getUserDataDto(String id) {
        UserComputationData data = this.getUserData(id);
        return new UserComputationDataMapper().entityToDto(data);
    }

    public void updateUserComputationData(UserComputationData data) {
        System.out.println(data.getId());
        System.out.println(data.getLatencyFaThreshold());
        System.out.println(data.getSocJetlagThreshold());
        System.out.println();
        this.repository.save(data);
    }
}
