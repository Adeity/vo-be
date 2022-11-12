package cz.cvut.fel.vyzkumodolnosti.model.entities.forms.submitted;

import cz.cvut.fel.vyzkumodolnosti.model.entities.forms.evaluations.MeqEvaluation;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
@DiscriminatorValue("meq")
public class MeqSubmittedForm extends SubmittedForm {
    @OneToOne(mappedBy = "submittedForm", cascade = CascadeType.ALL, orphanRemoval = true)
    private MeqEvaluation evaluation;

    public MeqEvaluation getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(MeqEvaluation evaluation) {
        this.evaluation = evaluation;
    }
}
