package cz.cvut.fel.vyzkumodolnosti.repository.forms.lifesatisfaction;

import cz.cvut.fel.vyzkumodolnosti.model.entities.forms.submitted.LifeSatisfactionSubmittedForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LifeSatisfactionSubmittedFormJpaRepository extends JpaRepository<LifeSatisfactionSubmittedForm, Integer> {
	@Query(value = "SELECT s " +
			"FROM LifeSatisfactionSubmittedForm s " +
			"ORDER BY s.respondentIdentifier, s.created")
	List<LifeSatisfactionSubmittedForm> findAllSortedByRespondentAndTime();
	@Query(value = "SELECT s " +
			"FROM LifeSatisfactionSubmittedForm s " +
			"ORDER BY s.created")
	List<LifeSatisfactionSubmittedForm> findAllSortedByTime();
}
