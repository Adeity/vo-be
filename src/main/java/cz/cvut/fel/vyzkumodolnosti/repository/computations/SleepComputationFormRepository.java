package cz.cvut.fel.vyzkumodolnosti.repository.computations;

import cz.cvut.fel.vyzkumodolnosti.model.entities.computations.SleepComputationForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SleepComputationFormRepository extends JpaRepository<SleepComputationForm, Long> {


    SleepComputationForm findById(long id);

    List<SleepComputationForm> findAll();

    List<SleepComputationForm> findAllByResearchParticipantResearchNumber(String uid);
}
