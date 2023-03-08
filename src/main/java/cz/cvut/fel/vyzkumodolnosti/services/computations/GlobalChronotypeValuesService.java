package cz.cvut.fel.vyzkumodolnosti.services.computations;

import cz.cvut.fel.vyzkumodolnosti.model.domain.computations.ChronotypeEnum;
import cz.cvut.fel.vyzkumodolnosti.model.dto.computations.SingleGlobalValueDto;
import cz.cvut.fel.vyzkumodolnosti.model.entities.computations.GlobalChronotypeValue;
import cz.cvut.fel.vyzkumodolnosti.repository.computations.GlobalSleepValuesJpaRepository;
import cz.cvut.fel.vyzkumodolnosti.services.computations.mappers.GlobalChronotypeValuesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GlobalChronotypeValuesService {

    @Autowired
    private GlobalSleepValuesJpaRepository repository;

    public List<GlobalChronotypeValue> getGlobalChronotypeValues() {
        return this.repository.findAll();
    }

    public void updateGlobalChronotypeValue(SingleGlobalValueDto dto) {
        System.out.println("updating");
        System.out.println(dto);
        repository.save(
                new GlobalChronotypeValuesMapper().dtoToEntity(dto)
        );
    }
}
