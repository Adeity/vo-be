package cz.cvut.fel.vyzkumodolnosti.repository.forms.questions;

import cz.cvut.fel.vyzkumodolnosti.handler.EntryNotFoundException;
import cz.cvut.fel.vyzkumodolnosti.model.entities.forms.questions.QuestionDefinition;
import cz.cvut.fel.vyzkumodolnosti.model.entities.forms.questions.QuestionWithAnswer;
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

	public QuestionDefinition findByCode(String code) {
		Optional<QuestionDefinition> questionDefinition = jpaRepository.findByCode(code);
		if (questionDefinition.isEmpty()) {
			throw new EntryNotFoundException("QuestionDefinition with code " + code + " not found.");
		}
		return questionDefinition.get();
	}

	public void addQuestionDefinitionToQuestionWithAnswer(Iterable<QuestionWithAnswer> questionWithAnswers) {
		for (QuestionWithAnswer questionWithAnswer : questionWithAnswers) {
			String code = questionWithAnswer.getQuestionDefinition().getCode();
			questionWithAnswer.setQuestionDefinition(this.findByCode(code));
		}
	}
}
