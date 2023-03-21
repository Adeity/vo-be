package cz.cvut.fel.vyzkumodolnosti.repository.computations;

import cz.cvut.fel.vyzkumodolnosti.model.entities.computations.GlobalChronotypeValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface GlobalSleepValuesJpaRepository extends JpaRepository<GlobalChronotypeValue, Integer> {

}
