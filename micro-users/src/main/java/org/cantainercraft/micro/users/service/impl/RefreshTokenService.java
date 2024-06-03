package org.cantainercraft.micro.users.service.impl;

import lombok.RequiredArgsConstructor;
import org.cantainercraft.micro.users.repository.TokenRepository;
import org.cantainercraft.micro.utilits.exception.NotResourceException;
import org.cantainercraft.project.entity.users.Token;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {
    private final TokenRepository tokenRepository;
    private final Function<Authentication,Token> tokenFactory;

    public void deleteToken(Integer id){
        var refreshToken = tokenRepository.findById(id);

        if(refreshToken.isEmpty()){
            throw new NotResourceException("token is not exist");
        }

        tokenRepository.deleteById(refreshToken.get().getId());
    }

    public Token createRefreshToken(Authentication authentication){
            Optional<Token> optionalToken = findByToken(authentication);

            if(optionalToken.isPresent()){
                return verifyException(optionalToken.get());
            }
            else {
                Token token = tokenFactory.apply(authentication);
                return tokenRepository.save(token);
            }
    }

    public Optional<Token> findByToken(Authentication authentication){
        String password = (String)authentication.getCredentials();
        return tokenRepository.findByUsernameAndPassword(authentication.getName(),password);
    }

    public Token verifyException(Token token){
        if(token.getExpiryDate().compareTo(Instant.now()) < 0){
            deleteToken(token.getId());
            throw new RuntimeException(token.getToken() + " Refresh token is expired. Please make a new login..!");
        }
        return token;
    }



}
