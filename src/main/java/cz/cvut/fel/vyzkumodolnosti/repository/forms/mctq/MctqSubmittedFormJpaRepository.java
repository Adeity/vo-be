package cz.cvut.fel.vyzkumodolnosti.repository.forms.mctq;

import cz.cvut.fel.vyzkumodolnosti.model.entities.forms.submitted.MctqSubmittedForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MctqSubmittedFormJpaRepository extends JpaRepository<MctqSubmittedForm, Integer> {
	@Query(value = "SELECT s " +
			"FROM MctqSubmittedForm s " +
			"ORDER BY s.respondentIdentifier, s.created")
	List<MctqSubmittedForm> findAllSortedByRespondentAndTime();
	@Query(value = "SELECT s " +
			"FROM MctqSubmittedForm s " +
			"ORDER BY s.created")
	List<MctqSubmittedForm> findAllSortedByTime();
}
