package cz.cvut.fel.pc2e.garminworker.model.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

@Entity
@Table(name = "device")
public class DeviceEntity {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(
            name = "id",
            nullable = false,
            unique = true
    )
    private int id;

    @Size(max = 255)
    @NotNull
    @Column(
            name = "device_id",
            nullable = false,
            unique = true
    )
    private String deviceId;

    @Column(
            name = "request_token"
    )
    private String requestToken;
    @Column(
            name = "request_token_secret"
    )
    private String requestTokenSecret;
    @Column(
            name = "request_token_verifier"
    )
    private String requestTokenVerifier;
    @Column(
            name = "oauth_token"
    )
    private String oauthToken;
    @Column(
            name = "oauth_token_secret"
    )
    private String oauthTokenSecret;

    @Column(
            name = "last_sync_time"
    )
    private Timestamp lastSyncTime;
    @Column(
            name = "user_id"
    )
    private String userId;

    private boolean allowed = false;

    public Timestamp getLastSyncTime() {
        return lastSyncTime;
    }

    public void setLastSyncTime(Timestamp lastSyncTime) {
        this.lastSyncTime = lastSyncTime;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
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

    public String getOauthToken() {
        return oauthToken;
    }

    public void setOauthToken(String oauthToken) {
        this.oauthToken = oauthToken;
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
}
