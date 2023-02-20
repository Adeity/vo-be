package cz.cvut.fel.vyzkumodolnosti.model.dto.forms.submitted;

import javax.validation.constraints.NotNull;

public class IdentifyingVariables {
    @NotNull
    private boolean hasResearchNumber;
    private String researchNumber;
    private String alternativeIdentifier;

    public boolean isHasResearchNumber() {
        return hasResearchNumber;
    }

    public void setHasResearchNumber(boolean hasResearchNumber) {
        this.hasResearchNumber = hasResearchNumber;
    }

    public String getResearchNumber() {
        return researchNumber;
    }

    public void setResearchNumber(String researchNumber) {
        this.researchNumber = researchNumber;
    }

    public String getAlternativeIdentifier() {
        return alternativeIdentifier;
    }

    public void setAlternativeIdentifier(String alternativeIdentifier) {
        this.alternativeIdentifier = alternativeIdentifier;
    }
}
