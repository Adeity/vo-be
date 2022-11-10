package cz.cvut.fel.vyzkumodolnosti.repository.forms.questions;

import cz.cvut.fel.vyzkumodolnosti.model.entities.forms.questions.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface QuestionDefinitionJpaRepository extends JpaRepository<Question, Integer> {
	Optional<Question> findByCode(String code);
}
