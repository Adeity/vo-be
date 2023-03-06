package cz.cvut.fel.vyzkumodolnosti.repository.forms.info;

import java.time.LocalDate;
import java.util.List;

public interface LifeSatisfactionSubmittedFormInfo {
    Integer getId();

    String getAlternativeIdentifier();

    LocalDate getCreated();

    ResearchParticipantInfo getResearchParticipant();

    List<AnswerInfo> getAnswers();

    LifeSatisfactionEvaluationInfo getEvaluation();
}
