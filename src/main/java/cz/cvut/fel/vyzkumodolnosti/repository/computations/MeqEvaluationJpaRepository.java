package cz.cvut.fel.vyzkumodolnosti.repository.computations;

import cz.cvut.fel.vyzkumodolnosti.model.entities.forms.evaluations.MeqEvaluation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface MeqEvaluationJpaRepository extends CrudRepository<MeqEvaluation, Integer> {
    @Query
            (value =
                    "SELECT * " +
                            "FROM meq_evaluation JOIN submitted_form sf " +
                            "on meq_evaluation.meq_submitted_form_id = sf.id" +
                            " WHERE sf.respondent_identifier = ?1" +
                            " ORDER BY created DESC LIMIT 1;",
                    nativeQuery=true)
    MeqEvaluation findNewestFromUser(String respId);
}
