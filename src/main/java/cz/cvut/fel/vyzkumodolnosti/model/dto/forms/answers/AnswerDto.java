package cz.cvut.fel.vyzkumodolnosti.model.dto.forms.answers;

import javax.validation.constraints.NotNull;

public class AnswerDto {
    @NotNull
    private String questionCode;
    @NotNull
    private String value;
    private String note;

    public String getQuestionCode() {
        return questionCode;
    }

    public void setQuestionCode(String questionCode) {
        this.questionCode = questionCode;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
