package org.cantainercraft.micro.chats.configuration;

import lombok.RequiredArgsConstructor;
import org.cantainercraft.micro.chats.configuration.filter.JwtAuthFilter;
import org.cantainercraft.micro.chats.service.impl.UserDetailServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import java.io.IOException;
import java.util.List;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SpringSecurity {
    private final UserDetailServiceImpl userDetailServiceImpl;
    private final JwtAuthFilter jwtAuthFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception, IOException {

        http.csrf(AbstractHttpConfigurer::disable)
                .cors(cors -> cors.configurationSource(request ->{
                   var corsConfiguration = new CorsConfiguration();
                   corsConfiguration.setAllowedMethods(List.of("POST","PUT","OPTION","HEADER","GET","DELETE"));
                   corsConfiguration.setAllowedHeaders(List.of("*"));
                   corsConfiguration.setAllowedOrigins(List.of("*"));
                   corsConfiguration.setAllowCredentials(true);
                   return corsConfiguration;
                }))
                .sessionManagement(sessionManagement  -> sessionManagement.sessionCreationPolicy(STATELESS) )
                .authorizeHttpRequests(request -> request
                        .requestMatchers("/emotions/**").permitAll()
                        .anyRequest().authenticated())
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }


    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        var authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        authenticationProvider.setUserDetailsService(userDetailServiceImpl);
        return authenticationProvider;
    }

}
