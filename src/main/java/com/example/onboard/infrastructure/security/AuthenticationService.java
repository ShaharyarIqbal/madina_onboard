package com.example.onboard.infrastructure.security;

import org.springframework.security.core.Authentication;


public interface AuthenticationService {

    /**
     * @param authentication
     * @return
     */
    JwtAuthenticationResponse authenticateUser(Authentication authentication);

}
