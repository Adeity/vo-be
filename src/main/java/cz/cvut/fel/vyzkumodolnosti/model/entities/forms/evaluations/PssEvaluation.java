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

    @NotNull
    @Column(name = "pss_sum", nullable = false)
    private Integer pssSum;

    @NotNull
    @Column(name = "pss_neg", nullable = false)
    private Integer pssNeg;

    @NotNull
    @Column(name = "pss_Pos", nullable = false)
    private Integer pssPos;


    public PssSubmittedForm getSubmittedForm() {
        return submittedForm;
    }

    public void setSubmittedForm(PssSubmittedForm submittedForm) {
        this.submittedForm = submittedForm;
    }


    public Integer getPssSum() {
        return pssSum;
    }

    public void setPssSum(Integer pssSum) {
        this.pssSum = pssSum;
    }

    public Integer getPssNeg() {
        return pssNeg;
    }

    public void setPssNeg(Integer pssNeg) {
        this.pssNeg = pssNeg;
    }

    public Integer getPssPos() {
        return pssPos;
    }

    public void setPssPos(Integer pssPos) {
        this.pssPos = pssPos;
    }
}
