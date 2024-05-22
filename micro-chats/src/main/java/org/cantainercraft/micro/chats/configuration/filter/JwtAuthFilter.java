
package org.cantainercraft.micro.chats.configuration.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.cantainercraft.micro.chats.service.impl.JwtService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * Фильтр для аутентификации JWT токенов, который выполняется один раз за запрос.
 */
@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    // Поля
    private final JwtService jwtService; // Сервис для работы с JWT токенами
    private final UserDetailsService userDetailsService; // Сервис для загрузки информации о пользователях

    /**
     * Метод, который выполняется для каждого HTTP запроса.
     *
     * @param request текущий HTTP запрос
     * @param response текущий HTTP ответ
     * @param filterChain цепочка фильтров
     * @throws ServletException если возникает ошибка сервлета
     * @throws IOException если возникает ошибка ввода-вывода
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String token = null;
        String username = null;

        // Извлечение JWT токена из cookies
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if (cookie.getName().equals("accessToken")) {
                    token = cookie.getValue();
                }
            }
        }

        // Если токен не найден, пропускаем фильтр
        if (token == null) {
            filterChain.doFilter(request, response);
            return;
        }

        // Извлечение имени пользователя из токена
        username = jwtService.extractUsername(token);

        if (username != null) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            // Проверка валидности токена
            if (jwtService.isTokenValid(token, userDetails)) {
                try {
                    // Создание объекта аутентификации
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                            userDetails.getUsername(),
                            userDetails.getPassword(),
                            userDetails.getAuthorities()
                    );
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                } catch (AuthenticationException exception) {
                    SecurityContextHolder.clearContext();
                }
            }
        }

        // Продолжение цепочки фильтров
        filterChain.doFilter(request, response);
    }
}
