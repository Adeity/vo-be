package cz.cvut.fel.vyzkumodolnosti.model.entities.forms.evaluations;

import cz.cvut.fel.vyzkumodolnosti.model.entities.AbstractEntity;
import cz.cvut.fel.vyzkumodolnosti.model.entities.forms.submitted.PssSubmittedForm;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "pss_evaluation")
public class PssEvaluation extends AbstractEntity {
    @NotNull
    @OneToOne
    @JoinColumn(name = "pss_submitted_form_id", referencedColumnName = "id")
    private PssSubmittedForm submittedForm;


    public PssSubmittedForm getSubmittedForm() {
        return submittedForm;
    }

    public void setSubmittedForm(PssSubmittedForm submittedForm) {
        this.submittedForm = submittedForm;
    }

}
