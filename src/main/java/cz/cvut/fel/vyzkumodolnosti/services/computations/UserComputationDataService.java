package cz.cvut.fel.vyzkumodolnosti.services.computations;

import cz.cvut.fel.vyzkumodolnosti.model.entities.computations.UserComputationData;
import cz.cvut.fel.vyzkumodolnosti.repository.computations.UserComputationDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserComputationDataService {

    @Autowired
    UserComputationDataRepository repository;

    public UserComputationData getUserData(String id) {
        return repository.findByUserId(id);
    }
}
