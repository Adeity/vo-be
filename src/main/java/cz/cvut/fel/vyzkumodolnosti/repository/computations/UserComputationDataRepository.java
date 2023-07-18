package cz.cvut.fel.vyzkumodolnosti.repository.computations;

import cz.cvut.fel.vyzkumodolnosti.model.entities.computations.UserComputationData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserComputationDataRepository extends JpaRepository<UserComputationData, String> {

    UserComputationData findByResearchParticipantResearchNumber(String researchNumber);

    List<UserComputationData> findAllByResearchParticipantResearchNumberIn(List<String> researchNumberList);
    @Query(value=
            "SELECT COUNT(u) FROM user_computation_data u " +
                    "JOIN research_participant rp on u.research_participant_research_number = rp.research_number" +
                    " INNER JOIN research_participant_methods rms on rms.research_participants_id = rp.id " +
                    " WHERE u.research_participant_research_number LIKE ?1 AND rms.methods_id = ?2",
            nativeQuery = true)
    Long countAllWithMethod(String queryString, Integer methodId);

    @Query(value=
            "SELECT COUNT(u) FROM user_computation_data u WHERE u.research_participant_research_number LIKE ?1",
            nativeQuery = true)
    Long countAll(String queryString);
}
