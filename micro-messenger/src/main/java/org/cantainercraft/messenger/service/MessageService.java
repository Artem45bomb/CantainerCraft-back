package org.cantainercraft.messenger.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import org.cantainercraft.messenger.dto.MessageDTO;
import java.util.UUID;

@Service
public class MessageService {
    private final WebClient webClient;

    public MessageService(WebClient webClient) {
        this.webClient = webClient;
    }

    public MessageDTO save(MessageDTO messageDTO){
        return webClient
                .post()
                .uri("message/add")
                .bodyValue(messageDTO)
                .retrieve()
                .bodyToFlux(MessageDTO.class)
                .blockFirst();
    }
    public Flux<MessageDTO> asyncSave(MessageDTO messageDTO){
        return webClient
                .post()
                .uri("message/add")
                .bodyValue(messageDTO)
                .retrieve()
                .bodyToFlux(MessageDTO.class);
    }

    public Flux<Boolean> delete(UUID uuid){
        return webClient
                .post()
                .uri("/message/delete")
                .bodyValue(uuid)
                .retrieve()
                .bodyToFlux(Boolean.class);
    }

    public Flux<MessageDTO> update(MessageDTO messageDTO){
        return webClient
                .post()
                .uri("/message/update")
                .bodyValue(messageDTO)
                .retrieve()
                .bodyToFlux(MessageDTO.class);
    }

    public MessageDTO findByUUID(UUID uuid){
        return webClient
                .post()
                .uri("/message/id")
                .bodyValue(uuid)
                .retrieve()
                .bodyToFlux(MessageDTO.class)
                .blockFirst();
    }


}
