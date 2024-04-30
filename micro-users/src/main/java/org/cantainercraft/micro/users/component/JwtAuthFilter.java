package org.cantainercraft.micro.users.component;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.cantainercraft.micro.users.service.UserService;
import org.cantainercraft.micro.users.service.impl.JwtService;
import org.cantainercraft.micro.users.service.impl.UserServiceDetailsImpl;
import org.cantainercraft.project.entity.Role;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
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


        if(username != null){
            UserDetails userDetails = userServiceDetails.loadUserByUsername(username);
            for(GrantedAuthority role : userDetails.getAuthorities()){
                System.out.println(role.toString()+"roo");;
            }
            if(jwtService.isTokenValid(token,userDetails)){
                UsernamePasswordAuthenticationToken  authenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails.getUsername(),
                        null,
                        userDetails.getAuthorities()
                );
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
        filterChain.doFilter(request,response);
    }
}
