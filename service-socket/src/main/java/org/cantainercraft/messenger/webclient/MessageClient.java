package org.cantainercraft.messenger.webclient;


import org.cantainercraft.messenger.dto.MessageDTO;
import org.cantainercraft.messenger.dto.MessageSearchDTO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.UUID;

@Component
public class MessageClient {
    private final WebClient webClient;

    @Value("${service.key}")
    private String serviceKey;
    
    public MessageClient(@Qualifier("chatsClient") WebClient webClient){
        this.webClient = webClient;
    }

    public MessageDTO findById(UUID uuid){
        return webClient
                .post()
                .uri("/message/uuid")
                .header("micro-service-key", serviceKey)
                .bodyValue(uuid)
                .retrieve()
                .bodyToMono(MessageDTO.class)
                .block();
    }

    public List<MessageDTO> findBySearch(MessageSearchDTO dto){
        return webClient
                .post()
                .uri("/message/search")
                .header("micro-service-key", serviceKey)
                .bodyValue(dto)
                .retrieve()
                .bodyToFlux(MessageDTO.class)
                .collectList()
                .block();
    }
    
    public MessageDTO save(MessageDTO messageDTO){
        return webClient
                .post()
                .uri("message/add")
                .header("micro-service-key", serviceKey)
                .bodyValue(messageDTO)
                .retrieve()
                .bodyToFlux(MessageDTO.class)
                .blockFirst();
    }
    
    public Flux<MessageDTO> asyncSave(MessageDTO messageDTO){
        return webClient
                .post()
                .uri("message/add")
                .header("micro-service-key", serviceKey)
                .bodyValue(messageDTO)
                .retrieve()
                .bodyToFlux(MessageDTO.class);
    }

    
    public Flux<Boolean> deleteByClientId(UUID uuid){
        return webClient
                .post()
                .uri("/message/delete/client/id")
                .header("micro-service-key", serviceKey)
                .bodyValue(uuid)
                .retrieve()
                .bodyToFlux(Boolean.class);
    }

    
    public Flux<MessageDTO> update(MessageDTO messageDTO){
        return webClient
                .post()
                .uri("/message/update")
                .header("micro-service-key", serviceKey)
                .bodyValue(messageDTO)
                .retrieve()
                .bodyToFlux(MessageDTO.class);
    }
}
