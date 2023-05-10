package cz.cvut.fel.vyzkumodolnosti.model.entities;

import cz.cvut.fel.vyzkumodolnosti.model.entities.sleeps.SleepSummary;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "device")
public class DeviceEntity implements Serializable {
    @OneToMany(mappedBy = "device", fetch = FetchType.LAZY)
    List<SleepSummary> sleepSummaries;
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private int id;

    @Size(max = 255)
    @NotNull
    @Column(name = "research_number", nullable = false, unique = true)
    private String researchNumber;
    @ManyToOne
    @JoinColumn(name = "research_participant_id", referencedColumnName = "id")
    private ResearchParticipant researchParticipant;

    @Column(name = "request_token")
    private String requestToken;
    @Column(name = "request_token_secret")
    private String requestTokenSecret;
    @Column(name = "request_token_verifier")
    private String requestTokenVerifier;
    @Column(name = "user_access_token", unique = true)
    private String userAccessToken;
    @Column(name = "oauth_token_secret")
    private String oauthTokenSecret;

    @Column(name = "last_sync_time")
    private Timestamp lastSyncTime;
    @Column(name = "user_id")
    private String userId;

    @Column(name = "allowed")
    private Boolean allowed = false;
    @Column(name = "deregistration_time")
    private LocalDate deregistrationTime;
    @Column(name = "registration_day")
    private Integer registrationDay;

    public DeviceEntity() {
    }

    public List<SleepSummary> getSleepSummaries() {
        if (sleepSummaries == null) {
            sleepSummaries = new ArrayList<>();
        }
        return sleepSummaries;
    }

    public DeviceEntity(String userAccessToken) {
        this.userAccessToken = userAccessToken;
    }

    public Timestamp getLastSyncTime() {
        return lastSyncTime;
    }

    public void setLastSyncTime(Timestamp lastSyncTime) {
        this.lastSyncTime = lastSyncTime;
    }

    public String getResearchNumber() {
        return researchNumber;
    }

    public void setResearchNumber(String deviceId) {
        this.researchNumber = deviceId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRequestToken() {
        return requestToken;
    }

    public void setRequestToken(String requestToken) {
        this.requestToken = requestToken;
    }

    public String getRequestTokenSecret() {
        return requestTokenSecret;
    }

    public void setRequestTokenSecret(String requestTokenSecret) {
        this.requestTokenSecret = requestTokenSecret;
    }

    public String getRequestTokenVerifier() {
        return requestTokenVerifier;
    }

    public void setRequestTokenVerifier(String requestTokenVerifier) {
        this.requestTokenVerifier = requestTokenVerifier;
    }

    public String getUserAccessToken() {
        return userAccessToken;
    }

    public void setUserAccessToken(String oauthToken) {
        this.userAccessToken = oauthToken;
    }

    public String getOauthTokenSecret() {
        return oauthTokenSecret;
    }

    public void setOauthTokenSecret(String oauthTokenSecret) {
        this.oauthTokenSecret = oauthTokenSecret;
    }

    public boolean isAllowed() {
        return allowed;
    }

    public void setAllowed(boolean allowed) {
        this.allowed = allowed;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Boolean getAllowed() {
        return allowed;
    }

    public void setAllowed(Boolean allowed) {
        this.allowed = allowed;
    }

    public LocalDate getDeregistrationTime() {
        return deregistrationTime;
    }

    public void setDeregistrationTime(LocalDate deregistrationTime) {
        this.deregistrationTime = deregistrationTime;
    }

    public ResearchParticipant getResearchParticipant() {
        return researchParticipant;
    }

    public void setResearchParticipant(ResearchParticipant researchParticipant) {
        this.researchParticipant = researchParticipant;
    }

    public Integer getRegistrationDay() {
        return registrationDay;
    }

    public void setRegistrationDay(Integer registrationDay) {
        this.registrationDay = registrationDay;
    }
}
