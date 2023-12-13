package com.ojas.securesafe.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionFixationProtectionStrategy;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class CustomSessionAuthenticationStrategy implements SessionAuthenticationStrategy {
    private final SessionAuthenticationStrategy delegate = new SessionFixationProtectionStrategy();

    @Override
    public void onAuthentication(Authentication authentication, HttpServletRequest request, HttpServletResponse response) throws SessionAuthenticationException {
        log.info("This is authentication"+authentication);
        log.info("This is request"+request);
        log.info("This is response"+response);
        delegate.onAuthentication(authentication, request, response);
    }
}
