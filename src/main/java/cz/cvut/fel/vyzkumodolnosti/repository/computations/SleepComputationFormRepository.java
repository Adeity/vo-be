package cz.cvut.fel.vyzkumodolnosti.repository.computations;

import cz.cvut.fel.vyzkumodolnosti.model.entities.computations.SleepComputationForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SleepComputationFormRepository extends JpaRepository<SleepComputationForm, Long> {


    SleepComputationForm findById(long id);

    List<SleepComputationForm> findAll();

    List<SleepComputationForm> findAllByResearchParticipantResearchNumberIn(List<String> researchNumberList);

    List<SleepComputationForm> findAllByRecalculations(Integer recalculations);

    List<SleepComputationForm> findAllByResearchParticipantResearchNumberAndRecalculations(String researchNumber, Integer recalculations);

    List<SleepComputationForm> findAllByResearchParticipantResearchNumber(String researchNumber);

    Integer countAllByVersionAndResearchParticipantResearchNumber(Integer versionn, String researchNumber);
}
