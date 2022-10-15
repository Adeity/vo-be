package cz.cvut.fel.vyzkumodolnosti.repository.forms;

import cz.cvut.fel.vyzkumodolnosti.handler.EntryNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Repository
public class SubmittedFormRepository<T> {
	@PersistenceContext
	EntityManager em;

	private final SubmittedFormJpaRepository<T> jpaRepository;

	@Autowired
	public SubmittedFormRepository(SubmittedFormJpaRepository<T> jpaRepository) {
		this.jpaRepository = jpaRepository;
	}

	public void save(T submittedForm) {
		this.jpaRepository.save(submittedForm);
	}

	public T findById(Integer id) {
		Optional<T> e = jpaRepository.findById(id);
		if (e.isEmpty()) {
			throw new EntryNotFoundException("Formular s id = " + id + " nebyl nalezen.");
		}
		return this.jpaRepository.findById(id).get();
	}
}
