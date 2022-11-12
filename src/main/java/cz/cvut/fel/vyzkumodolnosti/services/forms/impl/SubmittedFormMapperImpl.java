package cz.cvut.fel.vyzkumodolnosti.services.forms.impl;

import cz.cvut.fel.vyzkumodolnosti.handler.EntryNotFoundException;
import cz.cvut.fel.vyzkumodolnosti.model.dto.forms.answers.AnswerDto;
import cz.cvut.fel.vyzkumodolnosti.model.dto.forms.submitted.SubmittedFormDto;
import cz.cvut.fel.vyzkumodolnosti.model.entities.forms.questions.Answer;
import cz.cvut.fel.vyzkumodolnosti.model.entities.forms.questions.Question;
import cz.cvut.fel.vyzkumodolnosti.model.entities.forms.submitted.SubmittedForm;
import cz.cvut.fel.vyzkumodolnosti.repository.forms.QuestionRepository;
import cz.cvut.fel.vyzkumodolnosti.services.forms.api.SubmittedFormMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SubmittedFormMapperImpl implements SubmittedFormMapper {
    private final QuestionRepository questionRepository;

    @Autowired
    public SubmittedFormMapperImpl(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public SubmittedForm mapDtoToEntity(SubmittedFormDto dto) {
        SubmittedForm submittedForm = new SubmittedForm();
        submittedForm.setRespondentIdentifier(dto.getRespondentIdentifier());
        submittedForm.setResearchNumber(dto.getResearchNumber());

        for (AnswerDto answerDto : dto.getAnswers()) {
            submittedForm.getAnswers().add(mapAnswerDtoToEntity(answerDto));
        }

        return submittedForm;
    }

    private Answer mapAnswerDtoToEntity(AnswerDto dto) {
        Answer answer = new Answer();
        Optional<Question> questionOptional = questionRepository.findByCodeIgnoreCase(dto.getQuestionCode());
        if (questionOptional.isEmpty()) {
            throw new EntryNotFoundException("Question with code " + dto.getQuestionCode() + " does not exist");
        }
        answer.setQuestion(questionOptional.get());
        answer.setValue(dto.getValue());
        answer.setNote(dto.getNote());
        return answer;
    }
}
