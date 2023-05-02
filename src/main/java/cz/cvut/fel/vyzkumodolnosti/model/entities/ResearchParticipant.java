package cz.cvut.fel.vyzkumodolnosti.model.entities;

import cz.cvut.fel.vyzkumodolnosti.model.entities.forms.submitted.SubmittedForm;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "research_participant")
public class ResearchParticipant extends AbstractEntity {
    @NotNull
    @Column(name = "research_number", unique = true)
    private String researchNumber;
    @OneToOne
    @JoinColumn(name = "device_entity_id", referencedColumnName = "id")
    private DeviceEntity deviceEntity;
    @OneToMany(mappedBy = "researchParticipant")
    private List<SubmittedForm> submittedForms;
    @ManyToMany
    @JoinTable(name = "research_participant_methods",
            joinColumns =
            @JoinColumn(name = "research_participants_id"),
            inverseJoinColumns =
            @JoinColumn(name = "methods_id")
    )
    private List<Method> methods;

    public String getResearchNumber() {
        return researchNumber;
    }

    public void setResearchNumber(String researchNumber) {
        this.researchNumber = researchNumber;
    }

    public DeviceEntity getDeviceEntity() {
        return deviceEntity;
    }

    public void setDeviceEntity(DeviceEntity deviceEntity) {
        this.deviceEntity = deviceEntity;
    }

    public List<SubmittedForm> getSubmittedForms() {
        if (submittedForms == null) {
            submittedForms = new ArrayList<>();
        }
        return submittedForms;
    }

    public void setSubmittedForms(List<SubmittedForm> submittedForms) {
        this.submittedForms = submittedForms;
    }

    public List<Method> getMethods() {
        if (methods == null) {
            methods = new ArrayList<>();
        }
        return methods;
    }

    public void setMethods(List<Method> methods) {
        this.methods = methods;
    }
}
