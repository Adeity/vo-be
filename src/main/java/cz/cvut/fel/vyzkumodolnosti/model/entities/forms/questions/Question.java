package cz.cvut.fel.vyzkumodolnosti.model.entities.forms.questions;

import cz.cvut.fel.vyzkumodolnosti.model.entities.AbstractEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "question")
public class Question extends AbstractEntity {
    @NotNull
    @Column(name = "code", unique = true)
    private String code;
    @NotNull
    @Column(name = "label", columnDefinition = "text")
    private String label;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String text) {
        this.label = text;
    }

    @Override
    public String toString() {
        return "Question["+this.getId()+"]{" +
                "code='" + code + '\'' +
                ", label='" + label + '\'' +
                '}';
    }
}
