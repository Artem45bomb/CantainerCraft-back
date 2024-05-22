package org.cantainercraft.micro.chats.service.impl;

import org.cantainercraft.micro.utilits.service.JwtServiceBase;
import org.cantainercraft.micro.utilits.service.impl.JwtBaseServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class JwtService extends JwtBaseServiceImpl {

    /**
     * Конструктор, который инициализирует сервис с использованием секретного ключа и времени действия токена.
     *
     * @param SECRET_KEY секретный ключ для подписи JWT токенов
     * @param cookieTime время действия токена в миллисекундах
     */
    public JwtService(@Value("${token.signing.key}") String SECRET_KEY,
                      @Value("${duration.time.accessToken}") Long cookieTime){
        super(SECRET_KEY,cookieTime);
    }

    public boolean isTokenValid(String token, UserDetails userDetails){
        String username = extractUsername(token);
        return  username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }
}
