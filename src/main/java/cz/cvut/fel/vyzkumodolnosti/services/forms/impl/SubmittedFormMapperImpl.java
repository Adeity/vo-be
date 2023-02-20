package cz.cvut.fel.vyzkumodolnosti.services.forms.impl;

import cz.cvut.fel.vyzkumodolnosti.model.dto.forms.AnswerDto;
import cz.cvut.fel.vyzkumodolnosti.model.dto.forms.submitted.IdentifyingVariables;
import cz.cvut.fel.vyzkumodolnosti.model.dto.forms.submitted.SubmittedFormDto;
import cz.cvut.fel.vyzkumodolnosti.model.entities.ResearchParticipant;
import cz.cvut.fel.vyzkumodolnosti.model.entities.forms.questions.Answer;
import cz.cvut.fel.vyzkumodolnosti.model.entities.forms.questions.Question;
import cz.cvut.fel.vyzkumodolnosti.model.entities.forms.submitted.SubmittedForm;
import cz.cvut.fel.vyzkumodolnosti.repository.forms.ResearchParticipantRepository;
import cz.cvut.fel.vyzkumodolnosti.services.forms.api.SubmittedFormMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class SubmittedFormMapperImpl implements SubmittedFormMapper {

    public SubmittedForm mapDtoToEntity(SubmittedFormDto dto, IdentifyingVariables identifyingVariables) {
        SubmittedForm submittedForm = new SubmittedForm();


        return submittedForm;
    }

    private Answer mapAnswerDtoToEntity(AnswerDto dto) {
        Answer answer = new Answer();
        answer.setValue(dto.getAnswer());

        Question q = new Question();
        q.setId(dto.getQuestion().getId());
        answer.setQuestion(q);

        return answer;
    }
}
