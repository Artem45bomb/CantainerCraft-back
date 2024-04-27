package org.cantainercraft.micro.users.service.impl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.cantainercraft.micro.users.dto.CustomUserDetails;
import org.cantainercraft.project.entity.User;
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

//    @Value("${jwt.cookieExpiry}")
//    private long cookieExpiry;

    public String GenerateToken(UserDetails userDetails){
        Map<String, Object> claims = new HashMap<>();

        if(userDetails instanceof User user){
            claims.put("id",user.getId());
            claims.put("email",user.getEmail());
            claims.put("password",user.getPassword());
            claims.put("username",user.getName());
        }
        return createToken(claims,userDetails.getUsername());
    }

//    public boolean extractByUsername(String token,String username){
//
//    }

    public String extraUsername(String token){
        return extractClaim(token,Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims,T> claimsResolve){
        final Claims claims = extractAllClaims(token);
        return claimsResolve.apply(claims);
    }

    public Claims extractAllClaims(String token)
    {
       return Jwts
               .parser()
               .setSigningKey(getSignKey())
               .build()
               .parseClaimsJws(token)
               .getPayload();
    }

    public boolean isTokenValid(String token, UserDetails userDetails){
        String username = extraUsername(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpiry(token);
    }

    public boolean isTokenExpiry(String token){
        return  extractExpiration(token).before(new Date());
    }

    public Date extractExpiration(String token){
        return extractClaim(token,Claims::getExpiration);
    }

    public String createToken(Map<String,Object> claims,String username){
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + 60*24*10000);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(expiryDate)
                .signWith(getSignKey())
                .compact();
    }

    public Key getSignKey(){
        byte[] keys = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keys);
    }
}
