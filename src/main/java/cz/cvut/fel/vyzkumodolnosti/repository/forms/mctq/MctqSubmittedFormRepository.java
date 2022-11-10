package cz.cvut.fel.vyzkumodolnosti.repository.forms.mctq;

import cz.cvut.fel.vyzkumodolnosti.handler.EntryNotFoundException;
import cz.cvut.fel.vyzkumodolnosti.model.entities.forms.submitted.MctqSubmittedForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class MctqSubmittedFormRepository {

	private final MctqSubmittedFormJpaRepository MctqJpaRepository;

	@Autowired
	public MctqSubmittedFormRepository(MctqSubmittedFormJpaRepository MctqJpaRepository) {
		this.MctqJpaRepository = MctqJpaRepository;
	}

	public void save(MctqSubmittedForm submittedForm) {
		this.MctqJpaRepository.save(submittedForm);
	}

	public MctqSubmittedForm findById(Integer id) {
		Optional<MctqSubmittedForm> e = MctqJpaRepository.findById(id);
		if (e.isEmpty()) {
			throw new EntryNotFoundException("Formular s id = " + id + " nebyl nalezen.");
		}
		//noinspection OptionalGetWithoutIsPresent
		return this.MctqJpaRepository.findById(id).get();
	}

	public List<MctqSubmittedForm> findAll() {
		return MctqJpaRepository.findAll();
	}

	public List<MctqSubmittedForm> findAllSortedByTime() {
		return MctqJpaRepository.findAllSortedByTime();
	}

	public List<MctqSubmittedForm> findAllSortedByRespondentAndTime() {
		return MctqJpaRepository.findAllSortedByRespondentAndTime();
	}
}
