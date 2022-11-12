package cz.cvut.fel.vyzkumodolnosti.model.entities.forms.submitted;

import cz.cvut.fel.vyzkumodolnosti.model.entities.forms.evaluations.LifeSatisfactionEvaluation;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
@DiscriminatorValue("life_satisfaction")
public class LifeSatisfactionSubmittedForm extends SubmittedForm {
    @OneToOne(mappedBy = "submittedForm", cascade = CascadeType.ALL, orphanRemoval = true)
    private LifeSatisfactionEvaluation evaluation;

    public LifeSatisfactionEvaluation getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(LifeSatisfactionEvaluation evaluation) {
        this.evaluation = evaluation;
    }
}
