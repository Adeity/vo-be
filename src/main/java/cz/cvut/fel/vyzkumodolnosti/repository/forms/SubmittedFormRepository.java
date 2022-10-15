package cz.cvut.fel.vyzkumodolnosti.repository.forms;

import cz.cvut.fel.vyzkumodolnosti.model.entities.forms.submitted.PsqiSubmittedForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Repository
public class SubmittedFormRepository {
	@PersistenceContext
	EntityManager em;

	private final SubmittedFormJpaRepository<PsqiSubmittedForm> jpaRepository;

	@Autowired
	public SubmittedFormRepository(SubmittedFormJpaRepository<PsqiSubmittedForm> jpaRepository) {
		this.jpaRepository = jpaRepository;
	}

	public void save(PsqiSubmittedForm submittedForm) {
		this.jpaRepository.save(submittedForm);
	}

	public Optional<PsqiSubmittedForm> findById(Integer id) {
		return this.jpaRepository.findById(id);
	}
}
