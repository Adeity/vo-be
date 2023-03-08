package cz.cvut.fel.vyzkumodolnosti.repository.computations;

import cz.cvut.fel.vyzkumodolnosti.model.entities.forms.evaluations.PsqiEvaluation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface PsqiEvaluationJpaRepository extends CrudRepository<PsqiEvaluation, Integer> {

//    @Query
//            (value =
//            "SELECT * " +
//                    "FROM psqi_evaluation JOIN submitted_form sf " +
//                    "on psqi_evaluation.psqi_submitted_form_id = sf.id" +
//                    " ORDER BY created DESC;",
//            nativeQuery=true)
    List<PsqiEvaluation> findAllById(int id);

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
            "WHERE sf.created < ?2 " +
            "AND sf.respondent_identifier = ?1 " +
            "ORDER BY sf.created DESC LIMIT 1;",
        nativeQuery = true
    )
    PsqiEvaluation getClosestBeforeDate(String id, LocalDate date);
}
