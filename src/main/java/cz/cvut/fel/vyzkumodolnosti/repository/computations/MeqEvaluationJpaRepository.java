package cz.cvut.fel.vyzkumodolnosti.repository.computations;

import cz.cvut.fel.vyzkumodolnosti.model.entities.forms.evaluations.MeqEvaluation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface MeqEvaluationJpaRepository extends CrudRepository<MeqEvaluation, Integer> {
    @Query
            (value =
                    "SELECT * " +
                            "FROM meq_evaluation" +
                            " JOIN submitted_form sf on meq_evaluation.meq_submitted_form_id = sf.id" +
                            " LEFT JOIN research_participant rp on sf.research_participant_id = rp.id" +
                            " ORDER BY sf.created;",
                    nativeQuery=true)
    List<MeqEvaluation> findAllByOrderBySubmittedFormCreated();
    @Query
            (value =
                    "SELECT * " +
                            "FROM meq_evaluation JOIN submitted_form sf " +
                            "on meq_evaluation.meq_submitted_form_id = sf.id" +
                            " WHERE sf.respondent_identifier = ?1" +
                            " ORDER BY created DESC LIMIT 1;",
                    nativeQuery=true)
    MeqEvaluation findNewestFromUser(String respId);

    @Query(
            value =
                    "SELECT * " +
                            "FROM meq_evaluation JOIN submitted_form sf " +
                            "on meq_evaluation.meq_submitted_form_id = sf.id " +
                            "WHERE sf.created <= ?2 " +
                            "AND sf.respondent_identifier = ?1 " +
                            "ORDER BY sf.created DESC LIMIT 1;",
            nativeQuery = true
    )
    MeqEvaluation getClosestBeforeDate(String id, LocalDate date);

    @Query(value=
            "SELECT * " +
                    "FROM meq_evaluation JOIN submitted_form sf " +
                    "on meq_evaluation.meq_submitted_form_id = sf.id" +
                    " WHERE sf.respondent_identifier = ?1" +
                    " ORDER BY created DESC;",
            nativeQuery = true)
    public List<MeqEvaluation> getAllByRespId(String respId);

    public List<MeqEvaluation> getAllBySubmittedFormResearchParticipantResearchNumberInOrderBySubmittedFormCreated(List<String> researchNumberList);
    @Query(value=
            "SELECT * " +
                    "FROM meq_evaluation meq" +
                    " JOIN submitted_form sf on meq.meq_submitted_form_id = sf.id " +
                    " LEFT JOIN research_participant rp on sf.research_participant_id = rp.id" +
                    " WHERE rp.research_number IN ?1" +
                    " ORDER BY created DESC;",
            nativeQuery = true)
    public List<MeqEvaluation> getAllBySubmittedFormResearchParticipantResearchNumberIn(List<String> researchNumberList);
}
