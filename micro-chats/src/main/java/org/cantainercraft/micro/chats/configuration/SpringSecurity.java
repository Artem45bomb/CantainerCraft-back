package org.cantainercraft.micro.chats.configuration;

import lombok.RequiredArgsConstructor;
import org.cantainercraft.micro.chats.configuration.filter.JwtAuthFilter;
import org.cantainercraft.micro.chats.configuration.filter.ServiceAuthHandler;
import org.cantainercraft.micro.chats.service.impl.UserDetailServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import java.io.IOException;
import java.util.List;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

/**
 * Класс конфигурации безопасности Spring.
 * Этот класс настраивает параметры безопасности для вашего приложения,
 * включая аутентификацию, авторизацию и настройку CORS.
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SpringSecurity {

    // Поля
    private final UserDetailsService userDetailServiceImpl; // Сервис для получения информации о пользователях
    private final JwtAuthFilter jwtAuthFilter; // Фильтр для обработки JWT аутентификации
    private final ServiceAuthHandler serviceAuthHandler;

    /**
     * Конфигурирует цепочку фильтров безопасности.
     *
     * @param http объект HttpSecurity для настройки безопасности
     * @return настроенный SecurityFilterChain
     * @throws Exception если возникает ошибка конфигурации
     */

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception, IOException {
        http.csrf(AbstractHttpConfigurer::disable) // Отключение защиты от CSRF
                .cors(cors -> cors.configurationSource(request -> { // Настройка CORS
                    var corsConfiguration = new CorsConfiguration();
                    corsConfiguration.setAllowedMethods(List.of("POST", "PUT", "OPTION", "HEADER", "GET", "DELETE")); // Разрешенные методы
                    corsConfiguration.setAllowedHeaders(List.of("*")); // Разрешенные заголовки
                    corsConfiguration.setAllowedOrigins(List.of("*")); // Разрешенные источники
                    corsConfiguration.setAllowCredentials(true); // Разрешение отправки учетных данных
                    return corsConfiguration;
                }))
                .sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(STATELESS)) // Сессии не используются
                .authorizeHttpRequests(request -> request
                        .requestMatchers("/api/**").authenticated()
                        .requestMatchers("/swagger-ui/**").permitAll()
                        .anyRequest().permitAll()) // Все остальные запросы требуют аутентификации
                .authenticationProvider(authenticationProvider()) // Установка провайдера аутентификации
                .addFilterBefore(serviceAuthHandler,UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class); // Добавление JWT фильтра перед фильтром аутентификации
        return http.build();
    }
    /**
     * Создает и настраивает кодировщик паролей.
     *
     * @return объект PasswordEncoder для кодирования паролей
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Использование BCrypt для кодирования паролей
    }

    /**
     * Создает и настраивает провайдера аутентификации.
     *
     * @return объект AuthenticationProvider для аутентификации пользователей
     */
    @Bean
    public AuthenticationProvider authenticationProvider() {
        var authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(passwordEncoder()); // Установка кодировщика паролей
        authenticationProvider.setUserDetailsService(userDetailServiceImpl); // Установка сервиса для получения данных о пользователях
        return authenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

}
