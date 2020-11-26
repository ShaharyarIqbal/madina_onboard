package com.example.onboard.controller;



import com.example.onboard.constant.UserStatus;
import com.example.onboard.domain.dto.UserDto;
import com.example.onboard.domain.model.security.AuthenticationRequest;
import com.example.onboard.domain.model.security.UserEntity;
import com.example.onboard.infrastructure.security.AuthenticationService;
import com.example.onboard.infrastructure.security.JwtAuthenticationResponse;
import com.example.onboard.infrastructure.security.constant.SpringSecurity;
import com.example.onboard.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.net.URI;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/v1/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthenticationController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationController.class);


    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private UserService userService;

    @Autowired
    @Qualifier(value = "logoutSuccess")
    private LogoutSuccessHandler logoutSuccessHandler;

    @PostMapping(value = "/login")
    public JwtAuthenticationResponse authenticateUser(@RequestBody AuthenticationRequest authenticationRequest) {
        Optional<UserEntity> user =  userService.findByUserName(authenticationRequest.getUserName());
        try {

            if (user.isPresent() && user.get().getStatus() == UserStatus.INACTIVE) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, SpringSecurity.ACCOUNT_INACTIVE.getValue());
            }
            Authentication authentication = authenticationManager.authenticate(new
                    UsernamePasswordAuthenticationToken(authenticationRequest.getUserName(),
                    authenticationRequest.getPassword()));

                return authenticationService.authenticateUser(authentication);
        } catch (BadCredentialsException ex) {
            LOGGER.error(String.format("Authentication failed '%s'.", ex.getMessage()));
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, SpringSecurity.BAD_CREDENTIALS_UNAUTHORIZED.getValue());
        }

    }

    @GetMapping(value = "/logout")
    public void logout(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        logoutSuccessHandler.onLogoutSuccess(httpServletRequest, httpServletResponse, authentication);
        authentication.setAuthenticated(false);
        SecurityContextHolder.clearContext();
    }


    @PostMapping(value = "/register")
    public ResponseEntity<Object> register(@RequestBody @Valid UserDto userDto, HttpServletRequest request) {
        userService.addUser(userDto);
        return ResponseEntity.noContent().build();
    }
}
