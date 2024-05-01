package org.cantainercraft.micro.users.service.impl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.cantainercraft.micro.users.dto.CustomUserDetails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;


import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;


@Component
public class JwtService {
    @Value("${token.signing.key}")
    private String SECRET_KEY;

    public String extractRole(String token){
        return extractAllClaims(token).get("role",String.class);
    }

    public Date extractExpiry(String token){
        return extractClaim(token,Claims::getExpiration);
    }

    public boolean isTokenExpired(String token){
        return extractExpiry(token).before(new Date());
    }

    public boolean isTokenValid(String token,UserDetails userDetails){
        String username = extractUsername(token);
        return  username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token,Function<Claims,T> claimsResolve){
        final Claims claims = extractAllClaims(token);
        return claimsResolve.apply(claims);
    }


    public Claims extractAllClaims(String token){
        return Jwts
                .parser()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String createToken(Map<String,Object> claims,String username){

        Date date = new Date();
        Date expiry = new Date(date.getTime() + 10000* 24*60);
        return Jwts.builder()
                .setClaims(claims)  // Устанавливаем пользовательские claims
                .setSubject(username) // Устанавливаем имя пользователя в качестве темы токена (Subject)
                .setIssuedAt(date) // Устанавливаем метку времени выпуска токена
                .setExpiration(expiry) // Устанавливаем срок действия токена
                .signWith(getSignKey())
                .compact();

    }

    public String GenerateToken(UserDetails userDetails){
        Map<String,Object> claims = new HashMap<>();
        if(userDetails instanceof CustomUserDetails customUserDetails){
            claims.put("username", customUserDetails.getUsername());
        }
        return createToken(claims,userDetails.getUsername());
    }

    public Key getSignKey(){
        byte[] keys = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keys);
    }
}
