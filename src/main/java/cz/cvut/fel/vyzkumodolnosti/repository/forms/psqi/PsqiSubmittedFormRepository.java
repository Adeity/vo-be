package cz.cvut.fel.vyzkumodolnosti.repository.forms.psqi;

import cz.cvut.fel.vyzkumodolnosti.handler.EntryNotFoundException;
import cz.cvut.fel.vyzkumodolnosti.model.entities.forms.submitted.PsqiSubmittedForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PsqiSubmittedFormRepository {

	private final PsqiSubmittedFormJpaRepository psqiJpaRepository;

	@Autowired
	public PsqiSubmittedFormRepository(PsqiSubmittedFormJpaRepository psqiJpaRepository) {
		this.psqiJpaRepository = psqiJpaRepository;
	}

	public void save(PsqiSubmittedForm submittedForm) {
		this.psqiJpaRepository.save(submittedForm);
	}

	public PsqiSubmittedForm findById(Integer id) {
		Optional<PsqiSubmittedForm> e = psqiJpaRepository.findById(id);
		if (e.isEmpty()) {
			throw new EntryNotFoundException("Formular s id = " + id + " nebyl nalezen.");
		}
		//noinspection OptionalGetWithoutIsPresent
		return this.psqiJpaRepository.findById(id).get();
	}

	public List<PsqiSubmittedForm> findAll() {
		return psqiJpaRepository.findAll();
	}

	public List<PsqiSubmittedForm> findAllSortedByTime() {
		return psqiJpaRepository.findAllSortedByTime();
	}

	public List<PsqiSubmittedForm> findAllSortedByRespondentAndTime() {
		return psqiJpaRepository.findAllSortedByRespondentAndTime();
	}
}
