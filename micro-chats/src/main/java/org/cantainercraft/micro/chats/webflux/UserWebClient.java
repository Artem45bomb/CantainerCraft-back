package org.cantainercraft.micro.chats.webflux;

import lombok.RequiredArgsConstructor;
import org.cantainercraft.micro.chats.dto.CustomUserDetails;
import org.cantainercraft.project.entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserWebClient {

    private final WebClient webClient;
    @Value("${service.key}")
    private String serviceKey;

    public User loadedUser(String username){

        return  webClient
                .post()
                .uri("/user/loadedUser")
                .bodyValue(username)
                .header("micro-service-key",serviceKey)
                .retrieve()
                .bodyToMono(User.class)
                .block();
    }

    public Boolean userExist(Long userId){

        return  webClient
                .post()
                .uri("/user/exist/id")
                .bodyValue(userId)
                .header("micro-service-key",serviceKey)
                .retrieve()
                .bodyToMono(Boolean.class)
                .block();
    }
}
