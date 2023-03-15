package cz.cvut.fel.vyzkumodolnosti.repository.forms;

import cz.cvut.fel.vyzkumodolnosti.model.entities.ResearchParticipant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ResearchParticipantRepository extends JpaRepository<ResearchParticipant, Integer> {
    Optional<ResearchParticipant> findByResearchNumber(String researchNumber);
}
