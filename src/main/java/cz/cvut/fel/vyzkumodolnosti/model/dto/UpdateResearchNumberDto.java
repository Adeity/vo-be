package cz.cvut.fel.vyzkumodolnosti.model.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class UpdateResearchNumberDto {
    private int id;
    @NotNull
    @Pattern(regexp = "^[A-Z0-9]{3}_[A-Z0-9]{3}")
    private String newResearchNumber;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNewResearchNumber() {
        return newResearchNumber;
    }

    public void setNewResearchNumber(String newResearchNumber) {
        this.newResearchNumber = newResearchNumber;
    }
}
