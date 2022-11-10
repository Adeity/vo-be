package cz.cvut.fel.vyzkumodolnosti.repository.forms.meq;

import cz.cvut.fel.vyzkumodolnosti.model.entities.forms.submitted.MeqSubmittedForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MeqSubmittedFormJpaRepository extends JpaRepository<MeqSubmittedForm, Integer> {
	@Query(value = "SELECT s " +
			"FROM MeqSubmittedForm s " +
			"ORDER BY s.respondentIdentifier, s.created")
	List<MeqSubmittedForm> findAllSortedByRespondentAndTime();
	@Query(value = "SELECT s " +
			"FROM MeqSubmittedForm s " +
			"ORDER BY s.created")
	List<MeqSubmittedForm> findAllSortedByTime();
}
