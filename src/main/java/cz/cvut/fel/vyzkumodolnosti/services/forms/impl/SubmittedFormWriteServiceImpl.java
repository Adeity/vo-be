package cz.cvut.fel.vyzkumodolnosti.services.forms.impl;

import cz.cvut.fel.vyzkumodolnosti.model.dto.forms.AnswerDto;
import cz.cvut.fel.vyzkumodolnosti.model.dto.forms.submitted.*;
import cz.cvut.fel.vyzkumodolnosti.model.entities.ResearchParticipant;
import cz.cvut.fel.vyzkumodolnosti.model.entities.forms.questions.Answer;
import cz.cvut.fel.vyzkumodolnosti.model.entities.forms.questions.Question;
import cz.cvut.fel.vyzkumodolnosti.model.entities.forms.submitted.*;
import cz.cvut.fel.vyzkumodolnosti.repository.forms.*;
import cz.cvut.fel.vyzkumodolnosti.services.forms.api.ComputationVariablesEvaluator;
import cz.cvut.fel.vyzkumodolnosti.services.forms.api.SubmittedFormMapper;
import cz.cvut.fel.vyzkumodolnosti.services.forms.api.SubmittedFormWriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class SubmittedFormWriteServiceImpl implements SubmittedFormWriteService {
    private final PsqiSubmittedFormJpaRepository psqiRepository;
    private final MeqSubmittedFormJpaRepository meqRepository;
    private final MctqSubmittedFormJpaRepository mctqRepository;
    private final LifeSatisfactionSubmittedFormJpaRepository lifesatRepository;
    private final PssSubmittedFormRepository pssRepository;
    private final DemoSubmittedFormJpaRepository demoRepository;
    private final SubmittedFormMapper formMapper;
    private final ComputationVariablesEvaluator evaluator;
    private final ResearchParticipantRepository researchParticipantRepository;
    private final QuestionRepository questionRepository;

    @Autowired
    public SubmittedFormWriteServiceImpl(PsqiSubmittedFormJpaRepository psqiRepository, MeqSubmittedFormJpaRepository meqRepository, MctqSubmittedFormJpaRepository mctqRepository, LifeSatisfactionSubmittedFormJpaRepository lifesatRepository, SubmittedFormMapper formMapper, PssSubmittedFormRepository pssRepository, DemoSubmittedFormJpaRepository demoRepository, ResearchParticipantRepository researchParticipantRepository, QuestionRepository questionRepository) {
        this.psqiRepository = psqiRepository;
        this.meqRepository = meqRepository;
        this.mctqRepository = mctqRepository;
        this.lifesatRepository = lifesatRepository;
        this.formMapper = formMapper;
        this.pssRepository = pssRepository;
        this.demoRepository = demoRepository;
        this.researchParticipantRepository = researchParticipantRepository;
        this.questionRepository = questionRepository;
        this.evaluator = new ComputationVariablesEvaluatorImpl();
    }

    public void save(PsqiSubmittedFormDto dto, IdentifyingVariables identifyingVariables) {
        PsqiSubmittedForm entity = new PsqiSubmittedForm();
        setResearchParticipantVariables(entity, identifyingVariables);
        handleAnswers(entity, dto.getAnswers());

        entity.setEvaluation(evaluator.evaluate(dto.getComputationVariables()));
        entity.getEvaluation().setSubmittedForm(entity);

        psqiRepository.save(entity);
    }

    public void save(MctqSubmittedFormDto dto, IdentifyingVariables identifyingVariables) {
        MctqSubmittedForm entity = new MctqSubmittedForm();
        setResearchParticipantVariables(entity, identifyingVariables);
        handleAnswers(entity, dto.getAnswers());

        entity.setEvaluation(evaluator.evaluate(dto.getComputationVariables()));
        entity.getEvaluation().setSubmittedForm(entity);

        mctqRepository.save(entity);
    }

    public void save(MeqSubmittedFormDto dto, IdentifyingVariables identifyingVariables) {
        MeqSubmittedForm entity = new MeqSubmittedForm();
        setResearchParticipantVariables(entity, identifyingVariables);
        handleAnswers(entity, dto.getAnswers());

        entity.setEvaluation(evaluator.evaluate(dto.getComputationVariables()));
        entity.getEvaluation().setSubmittedForm(entity);

        meqRepository.save(entity);
    }

    public void save(PssSubmittedFormDto dto, IdentifyingVariables identifyingVariables) {
        PssSubmittedForm entity = new PssSubmittedForm();
        setResearchParticipantVariables(entity, identifyingVariables);
        handleAnswers(entity, dto.getAnswers());

        entity.setEvaluation(evaluator.evaluate(dto.getComputationVariables()));
        entity.getEvaluation().setSubmittedForm(entity);

        pssRepository.save(entity);
    }

    public void save(LifeSatisfactionSubmittedFormDto dto, IdentifyingVariables identifyingVariables) {
        LifeSatisfactionSubmittedForm entity = new LifeSatisfactionSubmittedForm();
        setResearchParticipantVariables(entity, identifyingVariables);
        handleAnswers(entity, dto.getAnswers());

        entity.setEvaluation(evaluator.evaluate(dto.getComputationVariables()));
        entity.getEvaluation().setSubmittedForm(entity);

        lifesatRepository.save(entity);
    }

    public void save(DemoSubmittedFormDto dto, IdentifyingVariables identifyingVariables) {
        DemoSubmittedForm entity = new DemoSubmittedForm();
        setResearchParticipantVariables(entity, identifyingVariables);
        handleAnswers(entity, dto.getAnswers());

        demoRepository.save(entity);
    }

    private void setResearchParticipantVariables(SubmittedForm submittedForm, IdentifyingVariables identifyingVariables) {
        submittedForm.setAlternativeIdentifier(identifyingVariables.getAlternativeIdentifier());
        if (identifyingVariables.isHasResearchNumber()) {
            Optional<ResearchParticipant> p = researchParticipantRepository
                    .findByResearchNumber(identifyingVariables.getResearchNumber());
            ResearchParticipant participant;
            if (p.isPresent()) {
                participant = p.get();
                submittedForm.setResearchParticipant(participant);
            } else {
                participant = new ResearchParticipant();
                Objects.requireNonNull(identifyingVariables.getResearchNumber());
                participant.setResearchNumber(identifyingVariables.getResearchNumber());
                researchParticipantRepository.save(participant);
            }
            submittedForm.setResearchParticipant(participant);
        }
    }

    private void handleAnswers(SubmittedForm submittedForm, List<AnswerDto> anwers) {
        for (AnswerDto answerDto : anwers) {
            submittedForm.getAnswers().add(mapAnswerDtoToEntity(answerDto, submittedForm));
        }
    }

    private Answer mapAnswerDtoToEntity(AnswerDto dto, SubmittedForm submittedForm) {
        Answer answer = new Answer();
        answer.setValue(dto.getAnswer());
        answer.setForm(submittedForm);

        Question question = questionRepository.getById(dto.getQuestion().getId());
        answer.setQuestion(question);

        return answer;
    }
}
