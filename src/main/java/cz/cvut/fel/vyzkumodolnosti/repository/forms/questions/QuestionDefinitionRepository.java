package cz.cvut.fel.vyzkumodolnosti.repository.forms.questions;

import cz.cvut.fel.vyzkumodolnosti.model.entities.forms.questions.QuestionDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class QuestionDefinitionRepository {
	private final QuestionDefinitionJpaRepository jpaRepository;

	@Autowired
	public QuestionDefinitionRepository(QuestionDefinitionJpaRepository jpaRepository) {
		this.jpaRepository = jpaRepository;
	}

	public Optional<QuestionDefinition> findByCode(String code) {
		return jpaRepository.findByCode(code);
	}
}
