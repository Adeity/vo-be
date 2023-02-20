package cz.cvut.fel.vyzkumodolnosti.model.dto.forms.submitted;

import cz.cvut.fel.vyzkumodolnosti.model.dto.forms.AnswerDto;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

public class SubmittedFormDto {
    @NotNull
    private List<@Valid AnswerDto> answers;

    public List<AnswerDto> getAnswers() {
        if (answers == null) {
            answers = new ArrayList<>();
        }
        return answers;
    }
}
