package com.example.onboard.infrastructure.security.filter;


import com.example.onboard.infrastructure.security.JwtTokenProvider;
import com.example.onboard.infrastructure.util.ValidationUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;

@Component
@Order(1)
public class JwtAuthenticationFilter implements Filter {

    private static final Logger LOGGER = LoggerFactory.getLogger(JwtAuthenticationFilter.class);
    @Autowired
    private JwtTokenProvider tokenProvider;

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
        try {

            String jwt = tokenProvider.getJwtFromRequest(httpRequest);
            if (ValidationUtils.isNotEmptyAndNull(jwt) && tokenProvider.validateToken(jwt)) {
                tokenProvider.setUserAuthentication(jwt);
            }

        } catch (Exception ex) {
            LOGGER.error("Could not set user authentication in security context", ex);
            HttpServletResponseWrapper wrapper = new HttpServletResponseWrapper(httpResponse);
            wrapper.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Un-Authorized or token expired");
            return;
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
