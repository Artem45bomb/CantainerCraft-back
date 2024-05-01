package org.cantainercraft.micro.users.configuration.filter;

import io.jsonwebtoken.lang.Collections;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cantainercraft.micro.users.service.impl.JwtService;
import org.cantainercraft.micro.users.service.impl.UserServiceDetailsImpl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtAuthFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final UserServiceDetailsImpl userServiceDetails;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {

        // Получаем токен из заголовка
        String username=null;
        String token =null;
        String roleAdd = null;
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

        if(request.getCookies() != null){
            for(Cookie cookie : request.getCookies()){
                if(cookie.getName().equals("accessToken")){
                    token = cookie.getValue();
                }
            }
        }

        if(token == null){
            filterChain.doFilter(request,response);
            return;
        }

        username = jwtService.extractUsername(token);
        roleAdd = jwtService.extractRole(token);

        if(username != null){
            UserDetails userDetails = userServiceDetails.loadUserByUsername(username);
            for(GrantedAuthority role : userDetails.getAuthorities()){
                System.out.println(role.toString()+"roo");;
            }
            if(jwtService.isTokenValid(token,userDetails)){
                if(roleAdd.equals("ROLE_ADMIN_TEMP")){
                    ResponseCookie responseCookie = ResponseCookie.from("accessToken")
                            .value(jwtService.GenerateToken(userDetails))
                            .httpOnly(true)
                            .secure(false)
                            .path("/")
                            .maxAge(10000*60*24)
                            .build();
                    response.addHeader(HttpHeaders.SET_COOKIE,responseCookie.toString());
                    grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_ADMIN_TEMP"));
                }
                grantedAuthorities.addAll(userDetails.getAuthorities());
                UsernamePasswordAuthenticationToken  authenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails.getUsername(),
                        null,
                        grantedAuthorities

                );
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
        filterChain.doFilter(request,response);
    }
}
