package cz.cvut.fel.vyzkumodolnosti.model.entities.computations;


import cz.cvut.fel.vyzkumodolnosti.model.entities.ResearchParticipant;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalTime;

@Entity
@Table(name = "user_computation_data")
@Getter
@Setter
public class UserComputationData implements Serializable {

    public UserComputationData(String userId, LocalTime socJetlagThreshold, Integer latencyFaThreshold) {
        this.researchParticipant = new ResearchParticipant();
        this.researchParticipant.setResearchNumber(userId);
        this.socJetlagThreshold = socJetlagThreshold;
        this.latencyFaThreshold = latencyFaThreshold;
    }

    public UserComputationData() {

    }

    @Id
    @Column(name="inner_id")
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id", referencedColumnName = "id")
    private ResearchParticipant researchParticipant;

    @Column(name="soc_jetlag_threshold")
    private LocalTime socJetlagThreshold;

    @Column(name="latency_fa_threshold")
    private Integer latencyFaThreshold;

    public LocalTime getSocJetlagThreshold() {
        return socJetlagThreshold;
    }

    public void setSocJetlagThreshold(LocalTime socJetlagThreshold) {
        this.socJetlagThreshold = socJetlagThreshold;
    }

    public Integer getLatencyFaThreshold() {
        return latencyFaThreshold;
    }

    public void setLatencyFaThreshold(Integer latencyFaThreshold) {
        this.latencyFaThreshold = latencyFaThreshold;
    }

    public String getId() {
        return this.researchParticipant.getResearchNumber();
    }
}
