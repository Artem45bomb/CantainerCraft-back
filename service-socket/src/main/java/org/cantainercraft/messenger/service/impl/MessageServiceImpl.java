package org.cantainercraft.messenger.service.impl;

import org.cantainercraft.messenger.dto.MessageDTO;
import org.cantainercraft.messenger.dto.MessageSearchDTO;
import org.cantainercraft.messenger.service.MessageService;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.UUID;

@Service
public class MessageServiceImpl implements MessageService {
    private final WebClient webClient;

    public MessageServiceImpl(WebClient webClient) {
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

    public Flux<Boolean> deleteByClientId(UUID uuid){
        return webClient
                .post()
                .uri("/message/delete/client/id")
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

    public List<MessageDTO> findBySearch(MessageSearchDTO searchDTO){
        return webClient
                .post()
                .uri("/messages//search")
                .bodyValue(searchDTO)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<MessageDTO>>() {
                })
                .block();
    }


}
