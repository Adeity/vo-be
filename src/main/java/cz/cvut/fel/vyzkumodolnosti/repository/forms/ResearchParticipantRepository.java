package cz.cvut.fel.vyzkumodolnosti.repository.forms;

import cz.cvut.fel.vyzkumodolnosti.model.entities.Method;
import cz.cvut.fel.vyzkumodolnosti.model.entities.ResearchParticipant;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ResearchParticipantRepository extends JpaRepository<ResearchParticipant, Integer> {
    Optional<ResearchParticipant> findByResearchNumber(String researchNumber);

    List<ResearchParticipant> findByResearchNumberLike(String queryString, Pageable pageable);
    List<ResearchParticipant> findByMethodsIsContainingAndResearchNumberLike(Method method, String queryString, Pageable pageable);

    List<ResearchParticipant> findByDeviceEntityRegistrationDayAndDeviceEntityDeregistrationTimeIsNull(int day);
}
