package cz.cvut.fel.vyzkumodolnosti.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import cz.cvut.fel.vyzkumodolnosti.security.model.LoginStatus;
import cz.cvut.fel.vyzkumodolnosti.security.model.UserDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

/**
 * Writes basic login/logout information into the response.
 */
@Service
public class AuthenticationSuccess implements AuthenticationSuccessHandler, LogoutSuccessHandler {

    private static final Logger LOG = LoggerFactory.getLogger(AuthenticationSuccess.class);

    private final ObjectMapper mapper;

    @Value("${localdev}")
    private Boolean LOCAL_DEV;

    @Autowired
    public AuthenticationSuccess(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                        Authentication authentication) throws IOException {
        final String username = getUsername(authentication);
        if (LOG.isTraceEnabled()) {
            LOG.trace("Successfully authenticated user {}", username);
        }
        final LoginStatus loginStatus = new LoginStatus(true, authentication.isAuthenticated(), username, null);
        if (LOCAL_DEV.equals(Boolean.FALSE)) {
            addSameSiteCookieAttribute(httpServletResponse);
        }
        mapper.writeValue(httpServletResponse.getOutputStream(), loginStatus);
    }

    private void addSameSiteCookieAttribute(HttpServletResponse httpServletResponse) {
        Collection<String> setCookieHeaders = httpServletResponse.getHeaders(HttpHeaders.SET_COOKIE);
        for (String header: setCookieHeaders) {
            httpServletResponse.setHeader(
                    HttpHeaders.SET_COOKIE,
                    String.format("%s; %s", header, "SameSite=none")
            );
        }
    }

    private String getUsername(Authentication authentication) {
        if (authentication == null) {
            return "";
        }
        return ((UserDetails) authentication.getPrincipal()).getUsername();
    }

    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                Authentication authentication) throws IOException {
        if (LOG.isTraceEnabled()) {
            LOG.trace("Successfully logged out user {}", getUsername(authentication));
        }
        final LoginStatus loginStatus = new LoginStatus(false, true, null, null);
        mapper.writeValue(httpServletResponse.getOutputStream(), loginStatus);
    }
}
