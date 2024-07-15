package org.cantainercraft.messenger.webclient;


import lombok.RequiredArgsConstructor;
import org.cantainercraft.messenger.dto.ProfileDTO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class ProfileClient {
    private final WebClient webClient;
    @Value("${service.key}")
    private String serviceKey;

    public ProfileClient(@Qualifier("usersClient") WebClient webClient){
        this.webClient =webClient;
    }

    public ProfileDTO update(ProfileDTO profileDTO){
        return webClient.put()
                .uri("/profile/update")
                .header("micro-service-key",serviceKey)
                .bodyValue(profileDTO)
                .retrieve()
                .bodyToMono(ProfileDTO.class)
                .block();
    }
}
