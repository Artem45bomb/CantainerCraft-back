package org.cantainercraft.micro.users.service.impl;

import org.cantainercraft.micro.utilits.service.impl.JwtBaseServiceImpl;
import org.cantainercraft.project.entity.users.Token;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/*
    JwtService used to work with a token;
     implements the basic interface JwtServiceBase
*/
@Component
public class JwtService extends JwtBaseServiceImpl {
    public JwtService(@Value("${token.signing.key}") String SECRET_KEY,
                      @Value("${duration.time.accessToken}") Long cookieTime){
        super(SECRET_KEY,cookieTime);
    }

    @Override
    public boolean isTokenValid(String token, Map<String,String> param){
        String usernameCheck = param.get("username");
        String username = extractUsername(token);
        return  username.equals(usernameCheck) && !isTokenExpired(token);
    }

    public String GenerateToken(Token token){
        Map<String,Object> claims = new HashMap<>();
        claims.put("username",token.getUsername());
        return createToken(claims,token.getUsername());
    }

}
