package cz.cvut.fel.vyzkumodolnosti.services.computations;

import cz.cvut.fel.vyzkumodolnosti.model.entities.forms.evaluations.MctqEvaluation;
import cz.cvut.fel.vyzkumodolnosti.model.entities.forms.evaluations.MeqEvaluation;
import cz.cvut.fel.vyzkumodolnosti.model.entities.forms.evaluations.PsqiEvaluation;
import cz.cvut.fel.vyzkumodolnosti.repository.computations.MctqEvaluationJpaRepository;
import cz.cvut.fel.vyzkumodolnosti.repository.computations.MeqEvaluationJpaRepository;
import cz.cvut.fel.vyzkumodolnosti.repository.computations.PsqiEvaluationJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FormsEvalService {

    @Autowired
    private PsqiEvaluationJpaRepository psqiRepository;
    @Autowired
    private MctqEvaluationJpaRepository mctqRepository;
    @Autowired
    private MeqEvaluationJpaRepository meqRepository;

    public List<PsqiEvaluation> getAllPsqiEvalsById(int userId) {
        return psqiRepository.findAllById(userId);
    }
    // TODO IMPLEMENT ME!
    public PsqiEvaluation getNewestPsqi(String userId) {
        return psqiRepository.findNewestFromUser(userId);
    }
    public MctqEvaluation getNewestMctq(String userId) {
        return mctqRepository.findNewestFromUser(userId);
    }
    public MeqEvaluation getNewestMeq(String userId) {
        return meqRepository.findNewestFromUser(userId);
    }
}
