package cz.cvut.fel.vyzkumodolnosti.repository.forms.questions;

import cz.cvut.fel.vyzkumodolnosti.handler.EntryNotFoundException;
import cz.cvut.fel.vyzkumodolnosti.model.entities.forms.questions.Question;
import cz.cvut.fel.vyzkumodolnosti.model.entities.forms.questions.Answer;
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

	public Question findByCode(String code) {
		Optional<Question> questionDefinition = jpaRepository.findByCode(code);
		if (questionDefinition.isEmpty()) {
			throw new EntryNotFoundException("QuestionDefinition with code " + code + " not found.");
		}
		return questionDefinition.get();
	}

	public void linkQuestionEntitiesToAnswers(Iterable<Answer> answers) {
		for (Answer answer : answers) {
			String code = answer.getQuestion().getCode();
			answer.setQuestion(this.findByCode(code));
		}
	}
}
