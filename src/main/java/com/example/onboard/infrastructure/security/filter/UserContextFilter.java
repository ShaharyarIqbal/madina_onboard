package com.example.onboard.infrastructure.security.filter;

import com.example.onboard.infrastructure.security.ExtendedServletRequestWrapper;
import com.example.onboard.infrastructure.security.JwtTokenProvider;

import com.example.onboard.infrastructure.security.constant.SpringSecurity;
import com.example.onboard.infrastructure.util.ValidationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;
import java.util.Map;




@Component
@Order(2)
public class UserContextFilter implements Filter {
    @Autowired
    private JwtTokenProvider tokenProvider;

    @Override
    public void destroy() {

    }

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws ServletException, IOException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
        ExtendedServletRequestWrapper extendedServletRequestWrapper =
                new ExtendedServletRequestWrapper(httpRequest);
        String jwt = tokenProvider.getJwtFromRequest(httpRequest);
        if (ValidationUtils.isNotEmptyAndNull(jwt)) {
            if (tokenProvider.validateToken(jwt)) {
                Map<String, Object> userContextMap = tokenProvider.getUserContextFromClaims(jwt);
                extendedServletRequestWrapper.addHeader(SpringSecurity.USER_ID.getValue(), userContextMap.get(SpringSecurity.USER_ID.getValue()).toString());
                extendedServletRequestWrapper.addHeader(SpringSecurity.USER_NAME.getValue(), userContextMap.get(SpringSecurity.USER_NAME.getValue()).toString());
                extendedServletRequestWrapper.addHeader(SpringSecurity.ROLES.getValue(),userContextMap.get(SpringSecurity.ROLES.getValue()).toString());

            } else {
                HttpServletResponseWrapper wrapper = new HttpServletResponseWrapper(httpResponse);
                wrapper.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Un-Authorized or token expired");
                return;
            }
        }
        filterChain.doFilter(extendedServletRequestWrapper, httpResponse);
    }


}
