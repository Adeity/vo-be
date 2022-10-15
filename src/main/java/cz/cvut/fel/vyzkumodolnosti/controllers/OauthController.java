package cz.cvut.fel.vyzkumodolnosti.controllers;

import com.google.api.client.auth.oauth.*;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import cz.cvut.fel.vyzkumodolnosti.model.entities.DeviceEntity;
import cz.cvut.fel.vyzkumodolnosti.repository.DeviceDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.io.Serializable;
import java.net.URI;
import java.util.Optional;

@Slf4j
@Controller
public class OauthController {

    private static final HttpTransport TRANSPORT = new NetHttpTransport();
    private final DeviceDao deviceRepository;
    @Value("${garmin.consumer.key}")
    private String CONSUMER_KEY;
    @Value("${garmin.consumer.secret}")
    private String CONSUMER_SECRET;
    @Value("${garmin.api.request.url}")
    private String REQUEST_URL;
    @Value("${garmin.api.authorize.url}")
    private String AUTHORIZE_URL;
    @Value("${garmin.api.access.url}")
    private String ACCESS_URL;
    @Value("${adminui.url}")
    private String ADMIN_UI_URL;

    public OauthController(DeviceDao deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    /**
     * Starts the oauth flow for new device with given ID
     *
     * @param deviceId identification of the device
     * @return redirect to the Garmin authorization site
     * @throws IOException if occurs in request processing
     */
    @GetMapping("/garmin/authorize")
    public ResponseEntity<Serializable> authorizeNewDevice(@RequestParam(name = "device_id") String deviceId) throws IOException {
        // check whether the device is not already authorized
        Optional<DeviceEntity> deviceEntityOpt = getDeviceAndCheckNotAuthorized(deviceId);

        // this signer will be used to sign all the requests in the "oauth dance"
        OAuthHmacSigner signer = new OAuthHmacSigner();
        signer.clientSharedSecret = CONSUMER_SECRET;

        // Step 1: Get a request token. This is a temporary token that is used for
        // having the user authorize an access token and to sign the request to obtain
        // said access token.
        OAuthGetTemporaryToken requestToken = new OAuthGetTemporaryToken(REQUEST_URL);
        requestToken.consumerKey = CONSUMER_KEY;
        requestToken.transport = TRANSPORT;
        requestToken.signer = signer;

        //requestToken.callback = "https://vyzkumodolnosti.felk.cvut.cz/garmin/oauthCallback";

        OAuthCredentialsResponse requestTokenResponse = requestToken.execute();
        log.info("Successfully received request token for device with deviceId: {}", deviceId);

        DeviceEntity deviceEntity;
        if (deviceEntityOpt.isPresent()) {
            deviceEntity = deviceEntityOpt.get();
            deviceEntity.setUserAccessToken(null);
            deviceEntity.setOauthTokenSecret(null);
        } else {
            deviceEntity = new DeviceEntity();
            deviceEntity.setResearchNumber(deviceId);
        }

        deviceEntity.setRequestToken(requestTokenResponse.token);
        deviceEntity.setRequestTokenSecret(requestTokenResponse.tokenSecret);
        deviceRepository.save(deviceEntity);

        log.debug("deviceId: {}    - request_token        = {}", deviceId, requestTokenResponse.token);
        log.debug("deviceId: {}    - request_token_secret = {}", deviceId, requestTokenResponse.tokenSecret);

        OAuthAuthorizeTemporaryTokenUrl authorizeUrl = new OAuthAuthorizeTemporaryTokenUrl(AUTHORIZE_URL);
        authorizeUrl.temporaryToken = requestTokenResponse.token;

        String oath_callback = "&oauth_callback=https://vyzkumodolnosti.felk.cvut.cz/garmin/oauthCallback";
        String authURL = authorizeUrl.buildAuthority();
        String relURL = authorizeUrl.buildRelativeUrl() + oath_callback;

        return ResponseEntity.status(HttpStatus.FOUND).location(URI.create(authURL+relURL)).build();
        //return ResponseEntity.status(HttpStatus.FOUND).location(URI.create(authorizeUrl.build())).build();
    }

    /**
     *
     * Processes the response of the oauth flow - stores the oauth token and its verifier in local DB and redirects user back to the admin UI
     *
     * @param requestToken oauth token of the device
     * @param verifier verifier of the oauth token
     * @return redirect to admin UI
     * @throws IOException if occurs in request processing
     */
    @GetMapping("/garmin/oauthCallback")
    @ResponseBody
    public ResponseEntity<Serializable> processOauthCallback(@RequestParam(name = "oauth_token") String requestToken, @RequestParam(name = "oauth_verifier") String verifier) throws IOException {
        log.info("Processing oAuth callback with requestToken: {}", requestToken);

        DeviceEntity deviceEntity = deviceRepository.findByRequestToken(requestToken).orElseThrow(() -> {
            log.error("Device with requestToken: {} not found", requestToken);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "OauthToken for unknown device");
        });

        deviceEntity.setRequestTokenVerifier(verifier);
        // saving in case of error will occur during next phase
        deviceRepository.save(deviceEntity);

        OAuthHmacSigner signer = new OAuthHmacSigner();
        signer.clientSharedSecret = CONSUMER_SECRET;
        signer.tokenSharedSecret = deviceEntity.getRequestTokenSecret();

        OAuthGetAccessToken accessToken = new OAuthGetAccessToken(
                ACCESS_URL);
        accessToken.consumerKey = CONSUMER_KEY;
        accessToken.signer = signer;
        accessToken.transport = TRANSPORT;
        accessToken.temporaryToken = requestToken;
        accessToken.verifier = verifier;

        OAuthCredentialsResponse accessTokenResponse = accessToken.execute();
        log.info("Successfully received access token for device with deviceId: {}", deviceEntity.getResearchNumber());

        deviceEntity.setUserAccessToken(accessTokenResponse.token);
        deviceEntity.setOauthTokenSecret(accessTokenResponse.tokenSecret);
        deviceEntity.setAllowed(true);
        deviceRepository.save(deviceEntity);

        log.debug("deviceId: {}    - access_token        = {}", deviceEntity.getResearchNumber(), accessTokenResponse.token);
        log.debug("deviceId: {}    - access_token_secret = {}", deviceEntity.getResearchNumber(), accessTokenResponse.tokenSecret);

        String authURL = "https://vyzkumodolnosti.felk.cvut.cz/";
        String relURL = "thanks.html";

        return ResponseEntity.status(HttpStatus.FOUND).location(URI.create(authURL+relURL)).build();
        //return "redirect" + "https://vyzkumodolnosti.felk.cvut.cz/" + "thanks.html";
        //return "redirect:" + ADMIN_UI_URL;
    }

    /**
     * Check whether the device is already authorized
     * If so, throws the 404 Bad Request exception
     * @param hwId of the device
     * @return device entity if exists of the unauthorized device
     */
    private Optional<DeviceEntity> getDeviceAndCheckNotAuthorized(String hwId) {
        Optional<DeviceEntity> deviceEntity = deviceRepository.findByResearchNumber(hwId);

        if (deviceEntity.isPresent() && deviceEntity.get().getUserAccessToken() != null) {
            log.warn("Device with deviceId {} already authorized", hwId);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Device already authorized");
        }
        return deviceEntity;
    }
}
