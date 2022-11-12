package cz.cvut.fel.vyzkumodolnosti.model.dto.forms.variables;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

public class LifeSatisfactionComputationVariablesDto {
    @NotNull
    @Size(min = 7, max = 7)
    private List<Integer> ZDR;

    @NotNull
    @Size(min = 7, max = 7)
    private List<Integer> PRZ;

    @NotNull
    @Size(min = 7, max = 7)
    private List<Integer> FIN;

    @NotNull
    @Size(min = 7, max = 7)
    private List<Integer> VOL;

    @NotNull
    @Size(min = 7, max = 7)
    private List<Integer> MAN;

    @Size(min = 7, max = 7)
    private List<Integer> DET;

    @NotNull
    @Size(min = 7, max = 7)
    private List<Integer> VLO;

    @NotNull
    @Size(min = 7, max = 7)
    private List<Integer> SEX;

    @NotNull
    @Size(min = 7, max = 7)
    private List<Integer> PZP;

    @NotNull
    @Size(min = 7, max = 7)
    private List<Integer> BYD;

    public List<Integer> getZDR() {
        return ZDR;
    }

    public void setZDR(List<Integer> ZDR) {
        this.ZDR = ZDR;
    }

    public List<Integer> getPRZ() {
        return PRZ;
    }

    public void setPRZ(List<Integer> PRZ) {
        this.PRZ = PRZ;
    }

    public List<Integer> getFIN() {
        return FIN;
    }

    public void setFIN(List<Integer> FIN) {
        this.FIN = FIN;
    }

    public List<Integer> getVOL() {
        return VOL;
    }

    public void setVOL(List<Integer> VOL) {
        this.VOL = VOL;
    }

    public List<Integer> getMAN() {
        return MAN;
    }

    public void setMAN(List<Integer> MAN) {
        this.MAN = MAN;
    }

    public List<Integer> getDET() {
        return DET;
    }

    public void setDET(List<Integer> DET) {
        this.DET = DET;
    }

    public List<Integer> getVLO() {
        return VLO;
    }

    public void setVLO(List<Integer> VLO) {
        this.VLO = VLO;
    }

    public List<Integer> getSEX() {
        return SEX;
    }

    public void setSEX(List<Integer> SEX) {
        this.SEX = SEX;
    }

    public List<Integer> getPZP() {
        return PZP;
    }

    public void setPZP(List<Integer> PZP) {
        this.PZP = PZP;
    }

    public List<Integer> getBYD() {
        return BYD;
    }

    public void setBYD(List<Integer> BYD) {
        this.BYD = BYD;
    }

}
