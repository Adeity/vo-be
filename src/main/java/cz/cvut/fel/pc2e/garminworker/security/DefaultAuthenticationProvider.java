package cz.cvut.fel.pc2e.garminworker.security;

import cz.cvut.fel.pc2e.garminworker.security.model.AuthenticationToken;
import cz.cvut.fel.pc2e.garminworker.security.model.UserDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class DefaultAuthenticationProvider implements AuthenticationProvider {

    private static final Logger LOG = LoggerFactory.getLogger(DefaultAuthenticationProvider.class);

    private final UserDetailsService userDetailsService;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public DefaultAuthenticationProvider(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UserDetails userDetails1;
        try {
             userDetails1 = (cz.cvut.fel.pc2e.garminworker.security.model.UserDetails) userDetailsService.loadUserByUsername(authentication.getPrincipal().toString());
        } catch (Exception e){
            throw e;
        }
        if (!passwordEncoder.matches((CharSequence) authentication.getCredentials(), userDetails1.getPassword())){
            throw new BadCredentialsException("Bad credentials!");
        }
        // pwd matches
        userDetails1.eraseCredentials();
        return SecurityUtils.setCurrentUser(userDetails1);
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(aClass) ||
                AuthenticationToken.class.isAssignableFrom(aClass);
    }


}
