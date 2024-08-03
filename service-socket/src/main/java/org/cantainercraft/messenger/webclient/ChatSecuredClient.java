package org.cantainercraft.messenger.webclient;

import org.cantainercraft.messenger.dto.ChatSecuredDTO;
import org.cantainercraft.messenger.dto.ProfileDTO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.UUID;

@Component
public class ChatSecuredClient {
    private final WebClient webClient;
    @Value("${service.key}")
    private String serviceKey;

    public ChatSecuredClient(@Qualifier("usersClient") WebClient webClient){
        this.webClient =webClient;
    }

    public ChatSecuredDTO save(ChatSecuredDTO dto){
        return webClient.post()
                .uri("/chat_secured/add")
                .header("micro-service-key",serviceKey)
                .bodyValue(dto)
                .retrieve()
                .bodyToMono(ChatSecuredDTO.class)
                .block();
    }

    public List<ChatSecuredDTO> findByUserIdChatSecured(Long userId){
        return webClient.get()
                .uri("/chat_secured/user/"+userId)
                .header("micro-service-key",serviceKey)
                .retrieve()
                .bodyToFlux(ChatSecuredDTO.class)
                .collectList()
                .block();
    }

    public void delete(ChatSecuredDTO dto){
         webClient.put()
                .uri("/chat_secured")
                .header("micro-service-key",serviceKey)
                .bodyValue(dto)
                .retrieve();
    }

    public void deleteById(UUID uuid){
        webClient.put()
                .uri("/chat_secured/id")
                .header("micro-service-key",serviceKey)
                .bodyValue(uuid)
                .retrieve();
    }
}
