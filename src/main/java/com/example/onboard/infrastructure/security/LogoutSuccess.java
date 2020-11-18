package com.example.onboard.infrastructure.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@Component(value = "logoutSuccess")
public class LogoutSuccess implements LogoutSuccessHandler {

    @Autowired
    private ObjectMapper objectMapper;


    /**
     * This method is used to logout the user. Custom implementation of logout success handler.
     * @param httpServletRequest
     *  http request
     * @param httpServletResponse
     *  http response
     * @param authentication
     *  user details in authentication parameter
     * @throws IOException
     */
    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException {
        Map<String, String> result = new HashMap<>();
        result.put( "data", "success" );
        result.put( "status", "true" );
        result.put( "statusCode", "200" );
        result.put( "statusMessage", "OK" );
        httpServletResponse.setContentType("application/json");
        httpServletResponse.getWriter().write( objectMapper.writeValueAsString( result ) );
        httpServletResponse.setStatus(HttpServletResponse.SC_OK);

    }
}