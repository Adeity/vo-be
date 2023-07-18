package cz.cvut.fel.vyzkumodolnosti.repository.computations;

import cz.cvut.fel.vyzkumodolnosti.model.entities.forms.evaluations.PsqiEvaluation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PsqiEvaluationJpaRepository extends CrudRepository<PsqiEvaluation, Integer> {

    @Query
            (value =
            "SELECT * " +
                    "FROM psqi_evaluation" +
                    " JOIN submitted_form sf on psqi_evaluation.psqi_submitted_form_id = sf.id" +
                    " LEFT JOIN research_participant rp on sf.research_participant_id = rp.id" +
                    " ORDER BY sf.created DESC;",
            nativeQuery=true)
    List<PsqiEvaluation> findAllByOrderBySubmittedFormCreated();

    List<PsqiEvaluation> findAllById(int id);

    long countBySubmittedFormResearchParticipantResearchNumber(String researchNumber);

    @Query(
                value =
            "SELECT * " +
                    "FROM psqi_evaluation JOIN submitted_form sf " +
                    "on psqi_evaluation.psqi_submitted_form_id = sf.id" +
                    " WHERE sf.respondent_identifier = ?1" +
                    " ORDER BY created DESC LIMIT 1;",
            nativeQuery=true)
        PsqiEvaluation findNewestFromUser(String respId);

    PsqiEvaluation findById(int id);

    @Query(
        value =
            "SELECT * " +
            "FROM psqi_evaluation JOIN submitted_form sf " +
            "on psqi_evaluation.psqi_submitted_form_id = sf.id " +
            "WHERE sf.created <= ?2 " +
            "AND sf.respondent_identifier = ?1 " +
            "ORDER BY sf.created DESC LIMIT 1;",
        nativeQuery = true
    )
    PsqiEvaluation getClosestBeforeDate(String id, LocalDate date);

    @Query(value=
            "SELECT * " +
                    "FROM psqi_evaluation JOIN submitted_form sf " +
                    "on psqi_evaluation.psqi_submitted_form_id = sf.id" +
                    " WHERE sf.respondent_identifier = ?1" +
                    " ORDER BY created DESC;",
            nativeQuery = true)
    public List<PsqiEvaluation> getAllByRespId(String respId);

    public List<PsqiEvaluation> getAllBySubmittedFormResearchParticipantResearchNumberInOrderBySubmittedFormCreated(List<String> researchNumberList);
    @Query(value=
            "SELECT * " +
                    "FROM psqi_evaluation psqi" +
                    " JOIN submitted_form sf on psqi.psqi_submitted_form_id = sf.id " +
                    " LEFT JOIN research_participant rp on sf.research_participant_id = rp.id" +
                    " WHERE rp.research_number IN ?1" +
                    " ORDER BY created DESC;",
            nativeQuery = true)
    public List<PsqiEvaluation> getAllBySubmittedFormResearchParticipantResearchNumberIn(List<String> researchNumbersList);

}
