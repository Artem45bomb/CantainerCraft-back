package org.cantainercraft.micro.users.service.impl;

import lombok.RequiredArgsConstructor;
import org.cantainercraft.micro.users.repository.RefreshTokenRepository;
import org.cantainercraft.micro.users.repository.UserRepository;
import org.cantainercraft.micro.utilits.exception.NotResourceException;
import org.cantainercraft.project.entity.RefreshToken;
import org.cantainercraft.project.entity.User;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {
    private final RefreshTokenRepository repositoryToken;
    private final UserRepository userRepository;


    public void deleteToken(Long userId){
        Optional<RefreshToken> refreshToken  = repositoryToken.findById(userId);

        if(refreshToken.isEmpty()){
            throw new NotResourceException("token is not exist");
        }

        repositoryToken.deleteById(userId);
    }

    public RefreshToken createRefreshToken(String username){
        Optional<User> user = userRepository.findByUsername(username);


        if(user.isPresent()){
            RefreshToken refreshToken = user.get().getRefreshToken();
            try {
                return verifyException(refreshToken);
            }
            catch (RuntimeException runtimeException){
                refreshToken = RefreshToken.builder()
                        .token(UUID.randomUUID().toString())
                        .expiryDate(Instant.now().plusMillis(6000000))
                        .user(user.get())
                        .build();
                return repositoryToken.save(refreshToken);
            }
        }else {
            throw new NotResourceException("user is not exist");
        }
    }

    public Optional<RefreshToken> findByToken(String token){
        return repositoryToken.findByToken(token);
    }

    public RefreshToken verifyException(RefreshToken refreshToken){
        if(refreshToken.getExpiryDate().compareTo(Instant.now()) < 0){
            repositoryToken.delete(refreshToken);
            throw new RuntimeException(refreshToken.getToken() + " Refresh token is expired. Please make a new login..!");
        }
        return refreshToken;
    }



}
