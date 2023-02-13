package cz.cvut.fel.vyzkumodolnosti.repository.computations;

import cz.cvut.fel.vyzkumodolnosti.model.entities.computations.SleepComputationForm;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SleepComputationFormRepository extends JpaRepository<SleepComputationForm, Long> {


    SleepComputationForm findById(long id);
}
