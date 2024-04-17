package ru.project.socket.chats.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ru.project.socket.chats.dto.ChatDTO;

import java.util.UUID;

@Service
public class ChatService {
    private final WebClient webClient;


    public ChatService(WebClient webClient) {
        this.webClient = webClient;
    }
    
    
    public Mono<ChatDTO> saveAsync(ChatDTO chatDTO){
        return webClient
                .post()
                .uri("chat/add")
                .bodyValue(chatDTO)
                .retrieve()
                .bodyToMono(ChatDTO.class);
    }
    
    public Mono<ChatDTO> updateAsync(ChatDTO chatDTO){
        return webClient
                .put()
                .uri("chat/update")
                .bodyValue(chatDTO)
                .retrieve()
                .bodyToMono(ChatDTO.class);
    }
    
    public Mono<Boolean> deleteAsync(UUID uuid){
        return webClient
                .put()
                .uri("delete")
                .bodyValue(uuid)
                .retrieve()
                .bodyToMono(Boolean.class);
                
    }

    public Mono<Boolean> deleteByNameAsync(UUID uuid){
        return webClient
                .put()
                .uri("delete/name")
                .bodyValue(uuid)
                .retrieve()
                .bodyToMono(Boolean.class);

    }
}
