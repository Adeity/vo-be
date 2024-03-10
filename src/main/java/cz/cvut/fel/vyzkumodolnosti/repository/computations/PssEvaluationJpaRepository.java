package cz.cvut.fel.vyzkumodolnosti.repository.computations;

import cz.cvut.fel.vyzkumodolnosti.model.entities.forms.evaluations.PssEvaluation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PssEvaluationJpaRepository extends CrudRepository<PssEvaluation, Integer> {

    @Query
            (value =
            "SELECT * " +
                    "FROM pss_evaluation" +
                    " JOIN submitted_form sf on pss_evaluation.pss_submitted_form_id = sf.id" +
                    " LEFT JOIN research_participant rp on sf.research_participant_id = rp.id" +
                    " ORDER BY sf.created DESC;",
            nativeQuery=true)
    List<PssEvaluation> findAllByOrderBySubmittedFormCreated();

    List<PssEvaluation> findAllById(int id);

    long countBySubmittedFormResearchParticipantResearchNumber(String researchNumber);

    @Query(
                value =
            "SELECT * " +
                    "FROM pss_evaluation JOIN submitted_form sf " +
                    "on pss_evaluation.pss_submitted_form_id = sf.id JOIN research_participant rp on sf.research_participant_id = rp.id" +
                    " WHERE rp.research_number = ?1" +
                    " ORDER BY created DESC LIMIT 1;",
            nativeQuery=true)
        PssEvaluation findNewestFromUser(String respId);

    PssEvaluation findById(int id);

    @Query(
        value =
            "SELECT * " +
            "FROM pss_evaluation JOIN submitted_form sf " +
            "on pss_evaluation.pss_submitted_form_id = sf.id JOIN research_participant rp on sf.research_participant_id = rp.id " +
            "WHERE sf.created <= ?2 " +
            "AND rp.research_number = ?1 " +
            "ORDER BY sf.created DESC LIMIT 1;",
        nativeQuery = true
    )
    PssEvaluation getClosestBeforeDate(String id, LocalDate date);

    @Query(value=
            "SELECT * " +
                    "FROM pss_evaluation JOIN submitted_form sf " +
                    "on pss_evaluation.pss_submitted_form_id = sf.id JOIN research_participant rp on sf.research_participant_id = rp.id " +
                    " WHERE rp.research_number = ?1" +
                    " ORDER BY created DESC;",
            nativeQuery = true)
    public List<PssEvaluation> getAllByRespId(String respId);

    public List<PssEvaluation> getAllBySubmittedFormResearchParticipantResearchNumberInOrderBySubmittedFormCreated(List<String> researchNumberList);
    @Query(value=
            "SELECT * " +
                    "FROM pss_evaluation pss" +
                    " JOIN submitted_form sf on pss.pss_submitted_form_id = sf.id " +
                    " LEFT JOIN research_participant rp on sf.research_participant_id = rp.id" +
                    " WHERE rp.research_number IN ?1" +
                    " ORDER BY created DESC;",
            nativeQuery = true)
    public List<PssEvaluation> getAllBySubmittedFormResearchParticipantResearchNumberIn(List<String> researchNumbersList);

}
