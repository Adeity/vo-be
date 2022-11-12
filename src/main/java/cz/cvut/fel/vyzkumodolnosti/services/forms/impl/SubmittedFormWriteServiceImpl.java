package cz.cvut.fel.vyzkumodolnosti.services.forms.impl;

import cz.cvut.fel.vyzkumodolnosti.model.dto.forms.submitted.LifeSatisfactionSubmittedFormDto;
import cz.cvut.fel.vyzkumodolnosti.model.dto.forms.submitted.MctqSubmittedFormDto;
import cz.cvut.fel.vyzkumodolnosti.model.dto.forms.submitted.MeqSubmittedFormDto;
import cz.cvut.fel.vyzkumodolnosti.model.dto.forms.submitted.PsqiSubmittedFormDto;
import cz.cvut.fel.vyzkumodolnosti.model.entities.forms.submitted.LifeSatisfactionSubmittedForm;
import cz.cvut.fel.vyzkumodolnosti.model.entities.forms.submitted.MctqSubmittedForm;
import cz.cvut.fel.vyzkumodolnosti.model.entities.forms.submitted.MeqSubmittedForm;
import cz.cvut.fel.vyzkumodolnosti.model.entities.forms.submitted.PsqiSubmittedForm;
import cz.cvut.fel.vyzkumodolnosti.repository.forms.LifeSatisfactionSubmittedFormJpaRepository;
import cz.cvut.fel.vyzkumodolnosti.repository.forms.MctqSubmittedFormJpaRepository;
import cz.cvut.fel.vyzkumodolnosti.repository.forms.MeqSubmittedFormJpaRepository;
import cz.cvut.fel.vyzkumodolnosti.repository.forms.PsqiSubmittedFormJpaRepository;
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
    private final SubmittedFormMapper formMapper;
    private final ComputationVariablesEvaluator evaluator;

    @Autowired
    public SubmittedFormWriteServiceImpl(PsqiSubmittedFormJpaRepository psqiRepository, MeqSubmittedFormJpaRepository meqRepository, MctqSubmittedFormJpaRepository mctqRepository, LifeSatisfactionSubmittedFormJpaRepository lifesatRepository, SubmittedFormMapper formMapper, ComputationVariablesEvaluator evaluator) {
        this.psqiRepository = psqiRepository;
        this.meqRepository = meqRepository;
        this.mctqRepository = mctqRepository;
        this.lifesatRepository = lifesatRepository;
        this.formMapper = formMapper;
        this.evaluator = evaluator;
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

    public void save(LifeSatisfactionSubmittedFormDto dto) {
        LifeSatisfactionSubmittedForm entity = (LifeSatisfactionSubmittedForm) formMapper.mapDtoToEntity(dto);
        entity.setEvaluation(evaluator.evaluate(dto.getVariablesDto()));
        lifesatRepository.save(entity);
    }
}
