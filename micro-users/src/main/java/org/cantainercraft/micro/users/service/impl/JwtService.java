package org.cantainercraft.micro.users.service.impl;

import org.cantainercraft.micro.utilits.service.impl.JwtBaseServiceImpl;
import org.cantainercraft.project.entity.Token;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@Component
public class JwtService extends JwtBaseServiceImpl {
    public JwtService(@Value("${token.signing.key}") String SECRET_KEY,
                      @Value("${duration.time.accessToken}") Long cookieTime){
        super(SECRET_KEY,cookieTime);
    }

    public boolean isTokenValid(String token,UserDetails userDetails){
        String username = extractUsername(token);
        return  username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    public String GenerateToken(Token token){
        Map<String,Object> claims = new HashMap<>();
        claims.put("username",token.getUsername());
        return createToken(claims,token.getUsername());
    }

}
