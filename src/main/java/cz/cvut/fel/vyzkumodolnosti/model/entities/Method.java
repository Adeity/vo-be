package cz.cvut.fel.vyzkumodolnosti.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "method")
public class Method extends AbstractEntity{
    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "methods")
    private List<ResearchParticipant> researchParticipants;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ResearchParticipant> getResearchParticipants() {
        if (researchParticipants == null) {
            researchParticipants = new ArrayList<>();
        }
        return researchParticipants;
    }

    public void setResearchParticipants(List<ResearchParticipant> researchParticipants) {
        this.researchParticipants = researchParticipants;
    }
}
