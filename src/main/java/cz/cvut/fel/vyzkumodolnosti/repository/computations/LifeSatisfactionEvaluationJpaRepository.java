package cz.cvut.fel.vyzkumodolnosti.repository.computations;

import cz.cvut.fel.vyzkumodolnosti.model.entities.forms.evaluations.LifeSatisfactionEvaluation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface LifeSatisfactionEvaluationJpaRepository extends CrudRepository<LifeSatisfactionEvaluation, Integer> {

    @Query
            (value =
            "SELECT * " +
                    "FROM life_sat_evaluation" +
                    " JOIN submitted_form sf on life_sat_evaluation.life_sat_submitted_form_id = sf.id" +
                    " LEFT JOIN research_participant rp on sf.research_participant_id = rp.id" +
                    " ORDER BY sf.created DESC;",
            nativeQuery=true)
    List<LifeSatisfactionEvaluation> findAllByOrderBySubmittedFormCreated();

    List<LifeSatisfactionEvaluation> findAllById(int id);

    long countBySubmittedFormResearchParticipantResearchNumber(String researchNumber);

    @Query(
                value =
            "SELECT * " +
                    "FROM life_sat_evaluation JOIN submitted_form sf " +
                    "on life_sat_evaluation.life_sat_submitted_form_id = sf.id JOIN research_participant rp on sf.research_participant_id = rp.id" +
                    " WHERE rp.research_number = ?1" +
                    " ORDER BY created DESC LIMIT 1;",
            nativeQuery=true)
        LifeSatisfactionEvaluation findNewestFromUser(String respId);

    LifeSatisfactionEvaluation findById(int id);

    @Query(
        value =
            "SELECT * " +
            "FROM life_sat_evaluation JOIN submitted_form sf " +
            "on life_sat_evaluation.life_sat_submitted_form_id = sf.id JOIN research_participant rp on sf.research_participant_id = rp.id " +
            "WHERE sf.created <= ?2 " +
            "AND rp.research_number = ?1 " +
            "ORDER BY sf.created DESC LIMIT 1;",
        nativeQuery = true
    )
    LifeSatisfactionEvaluation getClosestBeforeDate(String id, LocalDate date);

    @Query(value=
            "SELECT * " +
                    "FROM life_sat_evaluation JOIN submitted_form sf " +
                    "on life_sat_evaluation.life_sat_submitted_form_id = sf.id JOIN research_participant rp on sf.research_participant_id = rp.id " +
                    " WHERE rp.research_number = ?1" +
                    " ORDER BY created DESC;",
            nativeQuery = true)
    public List<LifeSatisfactionEvaluation> getAllByRespId(String respId);

    public List<LifeSatisfactionEvaluation> getAllBySubmittedFormResearchParticipantResearchNumberInOrderBySubmittedFormCreated(List<String> researchNumberList);
    @Query(value=
            "SELECT * " +
                    "FROM life_sat_evaluation pss" +
                    " JOIN submitted_form sf on pss.life_sat_submitted_form_id = sf.id " +
                    " LEFT JOIN research_participant rp on sf.research_participant_id = rp.id" +
                    " WHERE rp.research_number IN ?1" +
                    " ORDER BY created DESC;",
            nativeQuery = true)
    public List<LifeSatisfactionEvaluation> getAllBySubmittedFormResearchParticipantResearchNumberIn(List<String> researchNumbersList);

}
