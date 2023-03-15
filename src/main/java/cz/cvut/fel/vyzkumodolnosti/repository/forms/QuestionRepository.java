package cz.cvut.fel.vyzkumodolnosti.repository.forms;

import cz.cvut.fel.vyzkumodolnosti.model.entities.forms.questions.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {
    Optional<Question> findByCodeIgnoreCase(String code);
}