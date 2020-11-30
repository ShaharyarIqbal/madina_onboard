package com.example.onboard.infrastructure.security;


import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;



public class JwtAuthenticationResponse {
    private String accessToken;
    private String tokenType = "Bearer";
    private Collection<? extends GrantedAuthority> roles;
    private String userName;
    private  Long id;


    public JwtAuthenticationResponse(String accessToken, Collection<? extends GrantedAuthority> authorities, String userName,Long id) {
        this.accessToken = accessToken;
        this.roles = authorities;

        this.userName = userName;
        this.id=id;

    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public Collection<? extends GrantedAuthority> getRoles() {
        return roles;
    }

    public void setRoles(Collection<? extends GrantedAuthority> authorities) {
        this.roles = authorities;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

