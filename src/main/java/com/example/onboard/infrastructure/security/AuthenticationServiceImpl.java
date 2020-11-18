package com.example.onboard.infrastructure.security;

import com.example.onboard.domain.model.security.CustomUser;
import com.example.onboard.domain.model.security.UserEntity;
import com.example.onboard.infrastructure.security.constant.SpringSecurity;
import com.example.onboard.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private UserService userService;

    @Value("${app.login.retry.count}")
    private String retryCount;

    /**
     * {@inheritDoc}
     */
    @Override
    public JwtAuthenticationResponse authenticateUser(Authentication authentication) {

        SecurityContextHolder.getContext().setAuthentication(authentication);
        CustomUser userPrincipal = (CustomUser) authentication.getPrincipal();
        return new JwtAuthenticationResponse(tokenProvider.generateToken(authentication), authentication.getAuthorities(), userPrincipal.getUsername());
    }
}
