package cz.cvut.fel.pc2e.garminworker.entities.sleeps;

import cz.cvut.fel.pc2e.garminworker.entities.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "overall_sleep_score")
public class OverallSleepScore extends AbstractEntity {
    @NotNull
    @Column(
            name = "value"
    )
    private Integer score;
    @NotNull
    @Column(
            name = "qualifier_key"
    )
    private String qualifierKey;

    public OverallSleepScore() {
    }

    public OverallSleepScore(Integer score, String qualifierKey) {
        this.score = score;
        this.qualifierKey = qualifierKey;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer value) {
        this.score = value;
    }

    public String getQualifierKey() {
        return qualifierKey;
    }

    public void setQualifierKey(String qualifierKey) {
        this.qualifierKey = qualifierKey;
    }
}
