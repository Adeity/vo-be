package cz.cvut.fel.vyzkumodolnosti.model.dto.forms.submitted;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class FormInputDto {
    @Valid
    private PsqiSubmittedFormDto psqi;
    @Valid
    private LifeSatisfactionSubmittedFormDto dzs;
    @Valid
    private MctqSubmittedFormDto mctq;
    @Valid
    private MeqSubmittedFormDto meq;
    @Valid
    private PssSubmittedFormDto pss;
    @Valid
    @NotNull
    private IdentifyingVariables identifying;
    @Valid
    private DemoSubmittedFormDto demo;

    public PsqiSubmittedFormDto getPsqi() {
        return psqi;
    }

    public LifeSatisfactionSubmittedFormDto getDzs() {
        return dzs;
    }

    public MctqSubmittedFormDto getMctq() {
        return mctq;
    }

    public MeqSubmittedFormDto getMeq() {
        return meq;
    }

    public PssSubmittedFormDto getPss() {
        return pss;
    }

    public IdentifyingVariables getIdentifying() {
        return identifying;
    }

    public DemoSubmittedFormDto getDemo() {
        return demo;
    }
}
