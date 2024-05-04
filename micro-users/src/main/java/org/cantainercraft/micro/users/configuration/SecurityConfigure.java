package org.cantainercraft.micro.users.configuration;

import org.cantainercraft.micro.users.configuration.filter.JwtAuthFilter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.AuthenticationEntryPoint;

public class SecurityConfigure extends AbstractHttpConfigurer<SecurityConfigure, HttpSecurity> {

    private AuthenticationEntryPoint authenticationEntryPoint = ((request, response, authException) -> {
        response.addHeader(HttpHeaders.WWW_AUTHENTICATE,"TG");
        response.sendError(HttpStatus.UNAUTHORIZED.value());
    });

    @Override
    public void init(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.exceptionHandling(exceptionHandling -> exceptionHandling.authenticationEntryPoint(this.authenticationEntryPoint));
    }

    @Override
    public void configure(HttpSecurity httpSecurity){
        AuthenticationManager authenticationManager = httpSecurity.getSharedObject(AuthenticationManager.class);


    }

    public SecurityConfigure setAuthenticationEntryPoint(AuthenticationEntryPoint authenticationEntryPoint){
        this.authenticationEntryPoint = authenticationEntryPoint;
        return this;
    }
}
