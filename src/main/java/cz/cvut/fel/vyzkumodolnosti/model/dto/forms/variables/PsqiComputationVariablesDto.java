package cz.cvut.fel.vyzkumodolnosti.model.dto.forms.variables;

import javax.validation.constraints.Min;

public class PsqiComputationVariablesDto {
    private String q1;
    @Min(value = 0)
    private int q2;
    private String q3;
    private Double q4;
    private int q5a;
    private int q5b;
    private int q5c;
    private int q5d;
    private int q5e;
    private int q5f;
    private int q5g;
    private int q5h;
    private int q5i;
    private int q5j;
    private int q6;
    private int q7;
    private int q8;
    private int q9;
    // O volných dnech chodím spát v (HH:MM): (1)
    private String freeDaysGnt;
    // O volných dnech vstávám  v (HH:MM): (2)
    private String freeDaysGmt;
    // V pracovních dnech chodím spát v (HH:MM): (3)
    private String workDaysGnt;
    // V pracovních dnech vstávám v (HH:MM): (4)
    private String workDaysGmt;

    public String getQ1() {
        return q1;
    }

    public void setQ1(String q1) {
        this.q1 = q1;
    }

    public int getQ2() {
        return q2;
    }

    public void setQ2(int q2) {
        this.q2 = q2;
    }

    public String getQ3() {
        return q3;
    }

    public void setQ3(String q3) {
        this.q3 = q3;
    }

    public Double getQ4() {
        return q4;
    }

    public void setQ4(Double q4) {
        this.q4 = q4;
    }

    public int getQ5a() {
        return q5a;
    }

    public void setQ5a(int q5a) {
        this.q5a = q5a;
    }

    public int getQ5b() {
        return q5b;
    }

    public void setQ5b(int q5b) {
        this.q5b = q5b;
    }

    public int getQ5c() {
        return q5c;
    }

    public void setQ5c(int q5c) {
        this.q5c = q5c;
    }

    public int getQ5d() {
        return q5d;
    }

    public void setQ5d(int q5d) {
        this.q5d = q5d;
    }

    public int getQ5e() {
        return q5e;
    }

    public void setQ5e(int q5e) {
        this.q5e = q5e;
    }

    public int getQ5f() {
        return q5f;
    }

    public void setQ5f(int q5f) {
        this.q5f = q5f;
    }

    public int getQ5g() {
        return q5g;
    }

    public void setQ5g(int q5g) {
        this.q5g = q5g;
    }

    public int getQ5h() {
        return q5h;
    }

    public void setQ5h(int q5h) {
        this.q5h = q5h;
    }

    public int getQ5i() {
        return q5i;
    }

    public void setQ5i(int q5i) {
        this.q5i = q5i;
    }

    public int getQ5j() {
        return q5j;
    }

    public void setQ5j(int q5j) {
        this.q5j = q5j;
    }

    public int getQ6() {
        return q6;
    }

    public void setQ6(int q6) {
        this.q6 = q6;
    }

    public int getQ7() {
        return q7;
    }

    public void setQ7(int q7) {
        this.q7 = q7;
    }

    public int getQ8() {
        return q8;
    }

    public void setQ8(int q8) {
        this.q8 = q8;
    }

    public int getQ9() {
        return q9;
    }

    public void setQ9(int q9) {
        this.q9 = q9;
    }

    public String getFreeDaysGnt() {
        return freeDaysGnt;
    }

    public void setFreeDaysGnt(String freeDaysGnt) {
        this.freeDaysGnt = freeDaysGnt;
    }

    public String getFreeDaysGmt() {
        return freeDaysGmt;
    }

    public void setFreeDaysGmt(String freeDaysGmt) {
        this.freeDaysGmt = freeDaysGmt;
    }

    public String getWorkDaysGnt() {
        return workDaysGnt;
    }

    public void setWorkDaysGnt(String workDaysGnt) {
        this.workDaysGnt = workDaysGnt;
    }

    public String getWorkDaysGmt() {
        return workDaysGmt;
    }

    public void setWorkDaysGmt(String workDaysGmt) {
        this.workDaysGmt = workDaysGmt;
    }
}
