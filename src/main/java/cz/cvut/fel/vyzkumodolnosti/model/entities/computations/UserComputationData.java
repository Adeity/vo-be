package cz.cvut.fel.vyzkumodolnosti.model.entities.computations;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalTime;

@Entity
@Table(name = "user_computation_data")
public class UserComputationData {

    public UserComputationData(String userId, LocalTime socJetlagThreshold, Integer latencyFaThreshold) {
        this.userId = userId;
        this.socJetlagThreshold = socJetlagThreshold;
        this.latencyFaThreshold = latencyFaThreshold;
    }

    public UserComputationData() {

    }

    @Id
    @Column(name = "id")
    private String userId;

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

    public void setId(String id) {
        this.userId = id;
    }

    public String getId() {
        return userId;
    }
}
