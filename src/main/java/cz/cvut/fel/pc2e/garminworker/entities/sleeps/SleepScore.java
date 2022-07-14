package cz.cvut.fel.pc2e.garminworker.entities.sleeps;

import cz.cvut.fel.pc2e.garminworker.entities.AbstractEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(
        name = "sleep_score"
)
public class SleepScore extends AbstractEntity {
    @NotNull
    @Column(
            name = "sleep_score"
    )
    private String sleepScore;
    @NotNull
    @Column(
            name = "qualifier_key"
    )
    private String qualifierKey;

    public SleepScore() {
    }

    public SleepScore(String sleepScore, String qualifierKey) {
        this.sleepScore = sleepScore;
        this.qualifierKey = qualifierKey;
    }

    public String getSleepScore() {
        return sleepScore;
    }

    public void setSleepScore(String sleepScore) {
        this.sleepScore = sleepScore;
    }

    public String getQualifierKey() {
        return qualifierKey;
    }

    public void setQualifierKey(String qualifierKey) {
        this.qualifierKey = qualifierKey;
    }
}
