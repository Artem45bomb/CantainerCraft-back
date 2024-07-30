package org.cantainercraft.micro.utilits.service.impl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import lombok.Setter;
import org.cantainercraft.micro.utilits.service.JwtServiceBase;
import org.springframework.beans.factory.annotation.Value;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Getter
@Setter
public abstract class JwtBaseServiceImpl implements JwtServiceBase {
    //SECRET_KEY needed for encryption and decryption of data
    protected final String SECRET_KEY;
    // lifetime token
    protected final Long cookieTime;

    public JwtBaseServiceImpl(String SECRET_KEY,Long cookieTime){
        this.SECRET_KEY = SECRET_KEY;
        this.cookieTime =cookieTime;
    }

    @Override
    public Date extractExpiry(String token){
        return extractClaim(token, Claims::getExpiration);
    }

    @Override
    public boolean isTokenExpired(String token){
        return extractExpiry(token).before(new Date());
    }

    @Override
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    @Override
    public <T> T extractClaim(String token, Function<Claims,T> claimsResolve){
        final Claims claims = extractAllClaims(token);
        return claimsResolve.apply(claims);
    }


    @Override
    public Claims extractAllClaims(String token){
        return Jwts
                .parser()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    @Override
    public String createToken(Map<String,Object> claims, String username){

        Date date = new Date();
        Date expiry = new Date(date.getTime() +10000*24*60*cookieTime);
        return Jwts.builder()
                .setClaims(claims)  // Устанавливаем пользовательские claims
                .setSubject(username) // Устанавливаем имя пользователя в качестве темы токена (Subject)
                .setIssuedAt(date) // Устанавливаем метку времени выпуска токена
                .setExpiration(expiry) // Устанавливаем срок действия токена
                .signWith(getSignKey())
                .compact();

    }

    @Override
    public Key getSignKey(){
        byte[] keys = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keys);
    }

    public abstract boolean isTokenValid(String token,Map<String,String> param);
}
