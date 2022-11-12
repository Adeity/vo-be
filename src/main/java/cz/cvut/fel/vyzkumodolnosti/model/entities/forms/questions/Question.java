package cz.cvut.fel.vyzkumodolnosti.model.entities.forms.questions;

import cz.cvut.fel.vyzkumodolnosti.model.domain.forms.QuestionTypeEnum;
import cz.cvut.fel.vyzkumodolnosti.model.entities.AbstractEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "question", indexes = @Index(columnList = "code"))
public class Question extends AbstractEntity {
    @NotNull
    @Column(name = "code", unique = true)
    private String code;
    @NotNull
    @Column(name = "value", columnDefinition = "text")
    private String value;
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private QuestionTypeEnum type;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String text) {
        this.value = text;
    }

    public QuestionTypeEnum getType() {
        return type;
    }

    public void setType(QuestionTypeEnum type) {
        this.type = type;
    }
}
