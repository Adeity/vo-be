package cz.cvut.fel.vyzkumodolnosti.model.dto.forms.variables;

import javax.validation.constraints.Min;

public class PsqiComputationVariablesDto {
    private String q1;
    @Min(value = 0)
    private int q2;
    private String q3;
    private Double q4;
    private int q5aScore;
    private int q5bScore;
    private int q5cScore;
    private int q5dScore;
    private int q5eScore;
    private int q5fScore;
    private int q5gScore;
    private int q5hScore;
    private int q5iScore;
    private int q5jScore;
    private int q6Score;
    private int q7Score;
    private int q8Score;
    private int q9Score;

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

    public int getQ5aScore() {
        return q5aScore;
    }

    public void setQ5aScore(int q5aScore) {
        this.q5aScore = q5aScore;
    }

    public int getQ5bScore() {
        return q5bScore;
    }

    public void setQ5bScore(int q5bScore) {
        this.q5bScore = q5bScore;
    }

    public int getQ5cScore() {
        return q5cScore;
    }

    public void setQ5cScore(int q5cScore) {
        this.q5cScore = q5cScore;
    }

    public int getQ5dScore() {
        return q5dScore;
    }

    public void setQ5dScore(int q5dScore) {
        this.q5dScore = q5dScore;
    }

    public int getQ5eScore() {
        return q5eScore;
    }

    public void setQ5eScore(int q5eScore) {
        this.q5eScore = q5eScore;
    }

    public int getQ5fScore() {
        return q5fScore;
    }

    public void setQ5fScore(int q5fScore) {
        this.q5fScore = q5fScore;
    }

    public int getQ5gScore() {
        return q5gScore;
    }

    public void setQ5gScore(int q5gScore) {
        this.q5gScore = q5gScore;
    }

    public int getQ5hScore() {
        return q5hScore;
    }

    public void setQ5hScore(int q5hScore) {
        this.q5hScore = q5hScore;
    }

    public int getQ5iScore() {
        return q5iScore;
    }

    public void setQ5iScore(int q5iScore) {
        this.q5iScore = q5iScore;
    }

    public int getQ5jScore() {
        return q5jScore;
    }

    public void setQ5jScore(int q5jScore) {
        this.q5jScore = q5jScore;
    }

    public int getQ6Score() {
        return q6Score;
    }

    public void setQ6Score(int q6Score) {
        this.q6Score = q6Score;
    }

    public int getQ7Score() {
        return q7Score;
    }

    public void setQ7Score(int q7Score) {
        this.q7Score = q7Score;
    }

    public int getQ8Score() {
        return q8Score;
    }

    public void setQ8Score(int q8Score) {
        this.q8Score = q8Score;
    }

    public int getQ9Score() {
        return q9Score;
    }

    public void setQ9Score(int q9Score) {
        this.q9Score = q9Score;
    }
}
