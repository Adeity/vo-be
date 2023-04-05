package cz.cvut.fel.vyzkumodolnosti.model.entities.computations;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalTime;

@Entity
@Table(name = "global_chronotype_values")
public class GlobalChronotypeValue {

    @Id
    private Integer id;

    @Column(name="awakeFrom")
    private LocalTime awakeFrom;
    @Column(name="awakeTo")
    private LocalTime awakeTo;
    @Column(name="sleepFrom")
    private LocalTime sleepFrom;
    @Column(name="sleepTo")
    private LocalTime sleepTo;

    @Column(name="title")
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalTime getAwakeFrom() {
        return awakeFrom;
    }

    public void setAwakeFrom(LocalTime awakeFrom) {
        this.awakeFrom = awakeFrom;
    }

    public LocalTime getAwakeTo() {
        return awakeTo;
    }

    public void setAwakeTo(LocalTime awakeTo) {
        this.awakeTo = awakeTo;
    }

    public LocalTime getSleepFrom() {
        return sleepFrom;
    }

    public void setSleepFrom(LocalTime sleepFrom) {
        this.sleepFrom = sleepFrom;
    }

    public LocalTime getSleepTo() {
        return sleepTo;
    }

    public void setSleepTo(LocalTime sleepTo) {
        this.sleepTo = sleepTo;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}
