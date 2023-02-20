package cz.cvut.fel.vyzkumodolnosti.model.dto.forms;

import javax.validation.constraints.NotNull;

public class AnswerDto {
    @NotNull
    private QuestionDto question;
    @NotNull
    private String answer;

    public QuestionDto getQuestion() {
        return question;
    }

    public void setQuestion(QuestionDto question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
