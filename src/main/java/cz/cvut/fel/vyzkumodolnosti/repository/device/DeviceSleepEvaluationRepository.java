package cz.cvut.fel.vyzkumodolnosti.repository.device;

import cz.cvut.fel.vyzkumodolnosti.model.entities.device.DeviceSleepEvaluation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface DeviceSleepEvaluationRepository extends CrudRepository<DeviceSleepEvaluation, Integer> {
    List<DeviceSleepEvaluation> getAllByResearchParticipantResearchNumber(String respId);

    Long countByResearchParticipantResearchNumber(String respId);

    @Query(
            value =
                    "SELECT * " +
                            "FROM device_sleep_evaluation " +
                            "WHERE device_sleep_evaluation.created <= ?2 " +
                            "AND device_sleep_evaluation.research_participant_research_number = ?1 " +
                            "ORDER BY device_sleep_evaluation.created DESC LIMIT 1;",
            nativeQuery = true
    )
    DeviceSleepEvaluation getClosestBeforeDate(String id, LocalDate date);

    List<DeviceSleepEvaluation> getAllByResearchParticipantResearchNumberInOrderByCreated(List<String> researchNumberList);
    @Query(
            value =
                    "SELECT * " +
                            "FROM device_sleep_evaluation" +
                            " LEFT JOIN research_participant rp on research_participant_research_number = rp.research_number" +
                            " ORDER BY device_sleep_evaluation.created;",
            nativeQuery = true
    )
    List<DeviceSleepEvaluation> findAllOrderByCreated();
}
