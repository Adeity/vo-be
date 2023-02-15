package cz.cvut.fel.vyzkumodolnosti.services.forms.impl;

import cz.cvut.fel.vyzkumodolnosti.model.dto.forms.submitted.*;
import cz.cvut.fel.vyzkumodolnosti.model.entities.forms.submitted.*;
import cz.cvut.fel.vyzkumodolnosti.repository.forms.*;
import cz.cvut.fel.vyzkumodolnosti.services.forms.api.ComputationVariablesEvaluator;
import cz.cvut.fel.vyzkumodolnosti.services.forms.api.SubmittedFormMapper;
import cz.cvut.fel.vyzkumodolnosti.services.forms.api.SubmittedFormWriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubmittedFormWriteServiceImpl implements SubmittedFormWriteService {
    private final PsqiSubmittedFormJpaRepository psqiRepository;
    private final MeqSubmittedFormJpaRepository meqRepository;
    private final MctqSubmittedFormJpaRepository mctqRepository;
    private final LifeSatisfactionSubmittedFormJpaRepository lifesatRepository;
    private final PssSubmittedFormRepository pssRepository;
    private final SubmittedFormMapper formMapper;
    private final ComputationVariablesEvaluator evaluator;

    @Autowired
    public SubmittedFormWriteServiceImpl(PsqiSubmittedFormJpaRepository psqiRepository, MeqSubmittedFormJpaRepository meqRepository, MctqSubmittedFormJpaRepository mctqRepository, LifeSatisfactionSubmittedFormJpaRepository lifesatRepository, SubmittedFormMapper formMapper, PssSubmittedFormRepository pssRepository) {
        this.psqiRepository = psqiRepository;
        this.meqRepository = meqRepository;
        this.mctqRepository = mctqRepository;
        this.lifesatRepository = lifesatRepository;
        this.formMapper = formMapper;
        this.pssRepository = pssRepository;
        this.evaluator = new ComputationVariablesEvaluatorImpl();
    }

    public void save(PsqiSubmittedFormDto dto) {
        PsqiSubmittedForm entity = (PsqiSubmittedForm) formMapper.mapDtoToEntity(dto);
        entity.setEvaluation(evaluator.evaluate(dto.getVariablesDto()));
        psqiRepository.save(entity);
    }

    public void save(MctqSubmittedFormDto dto) {
        MctqSubmittedForm entity = (MctqSubmittedForm) formMapper.mapDtoToEntity(dto);
        entity.setEvaluation(evaluator.evaluate(dto.getVariablesDto()));
        mctqRepository.save(entity);
    }

    public void save(MeqSubmittedFormDto dto) {
        MeqSubmittedForm entity = (MeqSubmittedForm) formMapper.mapDtoToEntity(dto);
        entity.setEvaluation(evaluator.evaluate(dto.getVariablesDto()));
        meqRepository.save(entity);
    }

    public void save(PssSubmittedFormDto dto) {
        PssSubmittedForm entity = (PssSubmittedForm) formMapper.mapDtoToEntity(dto);
        entity.setEvaluation(evaluator.evaluate(dto.getVariablesDto()));
        pssRepository.save(entity);
    }

    public void save(LifeSatisfactionSubmittedFormDto dto) {
        LifeSatisfactionSubmittedForm entity = (LifeSatisfactionSubmittedForm) formMapper.mapDtoToEntity(dto);
        entity.setEvaluation(evaluator.evaluate(dto.getComputationVariables()));
        lifesatRepository.save(entity);
    }
}
