package org.cantainercraft.micro.chats.service.impl;

import org.cantainercraft.micro.utilits.service.JwtServiceBase;
import org.cantainercraft.micro.utilits.service.impl.JwtBaseServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class JwtService extends JwtBaseServiceImpl {
    public JwtService(@Value("${token.signing.key}") String SECRET_KEY,
                      @Value("${duration.time.accessToken}") Long cookieTime){
        super(SECRET_KEY,cookieTime);
    }

    public boolean isTokenValid(String token, UserDetails userDetails){
        String username = extractUsername(token);
        return  username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }
}
