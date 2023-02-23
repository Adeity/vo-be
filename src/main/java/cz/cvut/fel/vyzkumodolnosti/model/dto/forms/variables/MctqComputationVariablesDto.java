package cz.cvut.fel.vyzkumodolnosti.model.dto.forms.variables;

import javax.validation.constraints.NotNull;

public class MctqComputationVariablesDto {
    @NotNull
    private String btw;
    @NotNull
    private String sprepw;
    @NotNull
    private Integer slatw;
    @NotNull
    private String sew;
    @NotNull
    private Boolean alarmw;
    @NotNull
    private Integer siw;
    @NotNull
    private Integer wd;
    @NotNull
    private String lew;

    @NotNull
    private String btf;
    @NotNull
    private String sprepf;
    @NotNull
    private Integer slatf;
    @NotNull
    private String sef;
    @NotNull
    private Boolean alarmf;
    @NotNull
    private Integer sif;
    private Integer fd;
    @NotNull
    private String lef;

    public String getBtw() {
        return btw;
    }

    public void setBtw(String btw) {
        this.btw = btw;
    }

    public String getSprepw() {
        return sprepw;
    }

    public void setSprepw(String sprepw) {
        this.sprepw = sprepw;
    }

    public Integer getSlatw() {
        return slatw;
    }

    public void setSlatw(Integer slatw) {
        this.slatw = slatw;
    }

    public String getSew() {
        return sew;
    }

    public void setSew(String sew) {
        this.sew = sew;
    }

    public Boolean getAlarmw() {
        return alarmw;
    }

    public void setAlarmw(Boolean alarmw) {
        this.alarmw = alarmw;
    }

    public Integer getSiw() {
        return siw;
    }

    public void setSiw(Integer siw) {
        this.siw = siw;
    }

    public Integer getWd() {
        return wd;
    }

    public void setWd(Integer wd) {
        this.wd = wd;
    }

    public String getLew() {
        return lew;
    }

    public void setLew(String lew) {
        this.lew = lew;
    }

    public String getBtf() {
        return btf;
    }

    public void setBtf(String btf) {
        this.btf = btf;
    }

    public String getSprepf() {
        return sprepf;
    }

    public void setSprepf(String sprepf) {
        this.sprepf = sprepf;
    }

    public Integer getSlatf() {
        return slatf;
    }

    public void setSlatf(Integer slatf) {
        this.slatf = slatf;
    }

    public String getSef() {
        return sef;
    }

    public void setSef(String sef) {
        this.sef = sef;
    }

    public Boolean getAlarmf() {
        return alarmf;
    }

    public void setAlarmf(Boolean alarmf) {
        this.alarmf = alarmf;
    }

    public Integer getSif() {
        return sif;
    }

    public void setSif(Integer sif) {
        this.sif = sif;
    }

    public Integer getFd() {
        return fd;
    }

    public void setFd(Integer fd) {
        this.fd = fd;
    }

    public String getLef() {
        return lef;
    }

    public void setLef(String lef) {
        this.lef = lef;
    }
}
