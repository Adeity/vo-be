package cz.cvut.fel.vyzkumodolnosti.repository.forms.lifesatisfaction;

import cz.cvut.fel.vyzkumodolnosti.handler.EntryNotFoundException;
import cz.cvut.fel.vyzkumodolnosti.model.entities.forms.submitted.LifeSatisfactionSubmittedForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class LifeSatisfactionSubmittedFormRepository {

	private final LifeSatisfactionSubmittedFormJpaRepository LifeSatisfactionJpaRepository;

	@Autowired
	public LifeSatisfactionSubmittedFormRepository(LifeSatisfactionSubmittedFormJpaRepository LifeSatisfactionJpaRepository) {
		this.LifeSatisfactionJpaRepository = LifeSatisfactionJpaRepository;
	}

	public void save(LifeSatisfactionSubmittedForm submittedForm) {
		this.LifeSatisfactionJpaRepository.save(submittedForm);
	}

	public LifeSatisfactionSubmittedForm findById(Integer id) {
		Optional<LifeSatisfactionSubmittedForm> e = LifeSatisfactionJpaRepository.findById(id);
		if (e.isEmpty()) {
			throw new EntryNotFoundException("Formular s id = " + id + " nebyl nalezen.");
		}
		//noinspection OptionalGetWithoutIsPresent
		return this.LifeSatisfactionJpaRepository.findById(id).get();
	}

	public List<LifeSatisfactionSubmittedForm> findAll() {
		return LifeSatisfactionJpaRepository.findAll();
	}

	public List<LifeSatisfactionSubmittedForm> findAllSortedByTime() {
		return LifeSatisfactionJpaRepository.findAllSortedByTime();
	}

	public List<LifeSatisfactionSubmittedForm> findAllSortedByRespondentAndTime() {
		return LifeSatisfactionJpaRepository.findAllSortedByRespondentAndTime();
	}
}
