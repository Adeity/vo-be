package cz.cvut.fel.vyzkumodolnosti.model.entities.forms.submitted;

import cz.cvut.fel.vyzkumodolnosti.model.entities.AbstractEntity;
import cz.cvut.fel.vyzkumodolnosti.model.entities.ResearchParticipant;
import cz.cvut.fel.vyzkumodolnosti.model.entities.forms.questions.Answer;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "submitted_form")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "form_type", discriminatorType = DiscriminatorType.STRING)
public class SubmittedForm extends AbstractEntity {
    @Column(name = "alternative_identifier")
    private String alternativeIdentifier;
    @NotNull
    @ManyToOne
    @JoinColumn(name = "research_participant_id", referencedColumnName = "id")
    private ResearchParticipant researchParticipant;
    @NotNull
    @Column(name = "created")
    private LocalDate created;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "form", orphanRemoval = true)
    private List<Answer> answers;

    @PrePersist
    public void setCreatedPrePersist() {
        this.created = LocalDate.now();
    }

    public LocalDate getCreated() {
        return created;
    }

    public String getAlternativeIdentifier() {
        return alternativeIdentifier;
    }

    public void setAlternativeIdentifier(String alternativeIdentifier) {
        this.alternativeIdentifier = alternativeIdentifier;
    }

    public ResearchParticipant getResearchParticipant() {
        return researchParticipant;
    }

    public void setResearchParticipant(ResearchParticipant researchParticipant) {
        this.researchParticipant = researchParticipant;
    }

    public void setCreated(LocalDate created) {
        this.created = created;
    }

    public List<Answer> getAnswers() {
        if (answers == null) {
            answers = new ArrayList<>();
        }
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }
}
