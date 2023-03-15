package cz.cvut.fel.vyzkumodolnosti.repository.forms.info;

import cz.cvut.fel.vyzkumodolnosti.repository.forms.info.AnswerInfo;
import cz.cvut.fel.vyzkumodolnosti.repository.forms.info.ResearchParticipantInfo;

import java.time.LocalDate;
import java.util.List;

public interface DemoSubmittedFormInfo {
    Integer getId();

    String getAlternativeIdentifier();

    LocalDate getCreated();

    ResearchParticipantInfo getResearchParticipant();

    List<AnswerInfo> getAnswers();
}
