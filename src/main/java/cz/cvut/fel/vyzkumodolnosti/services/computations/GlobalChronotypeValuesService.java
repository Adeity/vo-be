package cz.cvut.fel.vyzkumodolnosti.services.computations;

import cz.cvut.fel.vyzkumodolnosti.model.entities.computations.GlobalChronotypeValue;
import cz.cvut.fel.vyzkumodolnosti.repository.computations.GlobalSleepValuesJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GlobalChronotypeValuesService {

    @Autowired
    private GlobalSleepValuesJpaRepository repository;

    public List<GlobalChronotypeValue> getGlobalChronotypeValues() {
        List<GlobalChronotypeValue> values = this.repository.findAll();
        return values;
    }
}
