package cz.cvut.fel.vyzkumodolnosti.services;

import cz.cvut.fel.vyzkumodolnosti.utils.OAuth1AuthorizationHeaderBuilder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OauthService {
    @Value("${garmin.consumer.key}")
    private String CONSUMER_KEY;
    @Value("${garmin.consumer.secret}")
    private String CONSUMER_SECRET;

    public String getOauth1Header(String OAUTH_TOKEN, String OAUTH_TOKEN_SECRET, String uri) {
        log.debug("Using consumer_secret {} and consumer_key {} to build Oauth Header", CONSUMER_SECRET, CONSUMER_KEY);
        String nonce = RandomStringUtils.randomAlphanumeric(32);
        String authorization = new OAuth1AuthorizationHeaderBuilder()
                .withMethod("GET")
                .withURL(uri)
                .withConsumerSecret(CONSUMER_SECRET)
                .withParameter("oauth_consumer_key", CONSUMER_KEY)
                .withParameter("oauth_token", OAUTH_TOKEN)
                .withParameter("oauth_nonce", nonce)
                .withTokenSecret(OAUTH_TOKEN_SECRET)
                .build();
        log.debug("Header: {}", authorization);
        return authorization;
    }
}
