package cz.cvut.fel.vyzkumodolnosti.repository.forms.meq;

import cz.cvut.fel.vyzkumodolnosti.handler.EntryNotFoundException;
import cz.cvut.fel.vyzkumodolnosti.model.entities.forms.submitted.MeqSubmittedForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class MeqSubmittedFormRepository {

	private final MeqSubmittedFormJpaRepository MeqJpaRepository;

	@Autowired
	public MeqSubmittedFormRepository(MeqSubmittedFormJpaRepository MeqJpaRepository) {
		this.MeqJpaRepository = MeqJpaRepository;
	}

	public void save(MeqSubmittedForm submittedForm) {
		this.MeqJpaRepository.save(submittedForm);
	}

	public MeqSubmittedForm findById(Integer id) {
		Optional<MeqSubmittedForm> e = MeqJpaRepository.findById(id);
		if (e.isEmpty()) {
			throw new EntryNotFoundException("Formular s id = " + id + " nebyl nalezen.");
		}
		//noinspection OptionalGetWithoutIsPresent
		return this.MeqJpaRepository.findById(id).get();
	}

	public List<MeqSubmittedForm> findAll() {
		return MeqJpaRepository.findAll();
	}

	public List<MeqSubmittedForm> findAllSortedByTime() {
		return MeqJpaRepository.findAllSortedByTime();
	}

	public List<MeqSubmittedForm> findAllSortedByRespondentAndTime() {
		return MeqJpaRepository.findAllSortedByRespondentAndTime();
	}
}
