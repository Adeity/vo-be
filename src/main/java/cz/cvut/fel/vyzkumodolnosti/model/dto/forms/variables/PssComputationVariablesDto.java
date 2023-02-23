package cz.cvut.fel.vyzkumodolnosti.model.dto.forms.variables;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class PssComputationVariablesDto {
    @NotNull
    @Min(0)
    @Max(4)
    private Integer q1; // pos
    @NotNull
    @Min(0)
    @Max(4)
    private Integer q2; // pos
    @NotNull
    @Min(0)
    @Max(4)
    private Integer q3; // pos
    @NotNull
    @Min(0)
    @Max(4)
    private Integer q4; // neg
    @NotNull
    @Min(0)
    @Max(4)
    private Integer q5; // neg
    @NotNull
    @Min(0)
    @Max(4)
    private Integer q6; // pos
    @NotNull
    @Min(0)
    @Max(4)
    private Integer q7; // neg
    @NotNull
    @Min(0)
    @Max(4)
    private Integer q8; // neg
    @NotNull
    @Min(0)
    @Max(4)
    private Integer q9; // pos
    @NotNull
    @Min(0)
    @Max(4)
    private Integer q10; // pos

    public Integer getQ1() {
        return q1;
    }

    public Integer getQ2() {
        return q2;
    }

    public Integer getQ3() {
        return q3;
    }

    public Integer getQ4() {
        return q4;
    }

    public Integer getQ5() {
        return q5;
    }

    public Integer getQ6() {
        return q6;
    }

    public Integer getQ7() {
        return q7;
    }

    public Integer getQ8() {
        return q8;
    }

    public Integer getQ9() {
        return q9;
    }

    public Integer getQ10() {
        return q10;
    }
}
