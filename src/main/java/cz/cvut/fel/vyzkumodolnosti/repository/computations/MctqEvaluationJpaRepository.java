package cz.cvut.fel.vyzkumodolnosti.repository.computations;

import cz.cvut.fel.vyzkumodolnosti.model.entities.forms.evaluations.MctqEvaluation;
import cz.cvut.fel.vyzkumodolnosti.model.entities.forms.evaluations.PsqiEvaluation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface MctqEvaluationJpaRepository extends CrudRepository<MctqEvaluation, Integer> {

    @Query
            (value =
                    "SELECT * " +
                            "FROM mctq_evaluation" +
                            " JOIN submitted_form sf on mctq_evaluation.mctq_submitted_form_id = sf.id" +
                            " LEFT JOIN research_participant rp on sf.research_participant_id = rp.id" +
                            " ORDER BY sf.created;",
                    nativeQuery=true)
    List<MctqEvaluation> findAllByOrderBySubmittedFormCreated();

    @Query
            (value =
                    "SELECT * " +
                            "FROM mctq_evaluation JOIN submitted_form sf " +
                            "on mctq_evaluation.mctq_submitted_form_id = sf.id" +
                            " WHERE sf.respondent_identifier = ?1" +
                            " ORDER BY created DESC LIMIT 1;",
                    nativeQuery=true)
    MctqEvaluation findNewestFromUser(String respId);

    @Query(
            value =
                    "SELECT * " +
                            "FROM mctq_evaluation JOIN submitted_form sf " +
                            "on mctq_evaluation.mctq_submitted_form_id = sf.id " +
                            "WHERE sf.created <= ?2 " +
                            "AND sf.respondent_identifier = ?1 " +
                            "ORDER BY sf.created DESC LIMIT 1;",
            nativeQuery = true
    )
    public MctqEvaluation getClosestBeforeDate(String id, LocalDate date);

    @Query(value=
            "SELECT * " +
            "FROM mctq_evaluation JOIN submitted_form sf " +
            "on mctq_evaluation.mctq_submitted_form_id = sf.id" +
            " WHERE sf.respondent_identifier = ?1" +
            " ORDER BY created DESC;",
    nativeQuery = true)
    public List<MctqEvaluation> getAllByRespId(String respId);

    public List<MctqEvaluation> getAllBySubmittedFormResearchParticipantResearchNumberInOrderBySubmittedFormCreated(List<String> researchNumberList);
    @Query(value=
            "SELECT * " +
                    "FROM mctq_evaluation mctq" +
                    " JOIN submitted_form sf on mctq.mctq_submitted_form_id = sf.id " +
                    " LEFT JOIN research_participant rp on sf.research_participant_id = rp.id" +
                    " WHERE rp.research_number IN ?1" +
                    " ORDER BY created DESC;",
            nativeQuery = true)
    public List<MctqEvaluation> getAllBySubmittedFormResearchParticipantResearchNumberIn(List<String> researchNumberList);
}
