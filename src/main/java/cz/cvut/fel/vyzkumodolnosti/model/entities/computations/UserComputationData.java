package cz.cvut.fel.vyzkumodolnosti.model.entities.computations;


import cz.cvut.fel.vyzkumodolnosti.model.entities.AbstractEntity;
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
public class UserComputationData extends AbstractEntity implements Serializable {

    public UserComputationData(ResearchParticipant rp, LocalTime socJetlagThreshold, Integer latencyFaThreshold) {
        this.researchParticipant = rp;
        this.socJetlagThreshold = socJetlagThreshold;
        this.latencyFaThreshold = latencyFaThreshold;
    }

    public UserComputationData() {

    }

    @OneToOne
    @JoinColumn(name = "research_participant_research_number", referencedColumnName = "research_number", unique = true)
    private ResearchParticipant researchParticipant;

    @Column(name="soc_jetlag_threshold")
    private LocalTime socJetlagThreshold;

    @Column(name="latency_fa_threshold")
    private Integer latencyFaThreshold;

}
