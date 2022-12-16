package cz.cvut.fel.vyzkumodolnosti.model.entities.forms.submitted;

import cz.cvut.fel.vyzkumodolnosti.model.entities.forms.evaluations.PssEvaluation;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
@DiscriminatorValue("pss")
public class PssSubmittedForm extends SubmittedForm {
    @OneToOne(mappedBy = "submittedForm", cascade = CascadeType.ALL, orphanRemoval = true)
    private PssEvaluation evaluation;

    public PssEvaluation getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(PssEvaluation evaluation) {
        this.evaluation = evaluation;
    }
}
