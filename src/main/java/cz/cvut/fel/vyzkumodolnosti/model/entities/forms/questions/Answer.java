package cz.cvut.fel.vyzkumodolnosti.model.entities.forms.questions;

import cz.cvut.fel.vyzkumodolnosti.model.entities.AbstractEntity;
import cz.cvut.fel.vyzkumodolnosti.model.entities.forms.submitted.SubmittedForm;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "answer")
public class Answer extends AbstractEntity {
    @NotNull
    @ManyToOne
    @JoinColumn(name = "question_definition_id", referencedColumnName = "id")
    private Question question;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "form_id", referencedColumnName = "id")
    private SubmittedForm form;

    @NotNull
    @Column(name = "value", nullable = false)
    private String value;

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String answer) {
        this.value = answer;
    }

    public SubmittedForm getForm() {
        return form;
    }

    public void setForm(SubmittedForm form) {
        this.form = form;
    }
}
