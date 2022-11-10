package cz.cvut.fel.vyzkumodolnosti.repository.forms.psqi;

import cz.cvut.fel.vyzkumodolnosti.model.entities.forms.submitted.PsqiSubmittedForm;
import cz.cvut.fel.vyzkumodolnosti.model.entities.forms.submitted.SubmittedForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PsqiSubmittedFormJpaRepository extends JpaRepository<PsqiSubmittedForm, Integer> {
	@Query(value = "SELECT s " +
			"FROM PsqiSubmittedForm s " +
			"ORDER BY s.respondentIdentifier, s.created")
	List<PsqiSubmittedForm> findAllSortedByRespondentAndTime();
	@Query(value = "SELECT s " +
			"FROM PsqiSubmittedForm s " +
			"ORDER BY s.created")
	List<PsqiSubmittedForm> findAllSortedByTime();
}
