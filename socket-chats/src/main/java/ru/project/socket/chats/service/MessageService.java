package ru.project.socket.chats.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.project.socket.chats.dto.MessageDTO;

import java.time.LocalDate;

@Service
public class MessageService {
    private final WebClient webClient;

    public MessageService(WebClient webClient) {
        this.webClient = webClient;
    }

    public Flux<MessageDTO> save(MessageDTO messageDTO){
        messageDTO.setLocalDate(LocalDate.now());
        return webClient
                .post()
                .uri("/message/add")
                .bodyValue(messageDTO)
                .retrieve()
                .bodyToFlux(MessageDTO.class);
    }

    public void delete(){

    }

    public void update(){

    }

    public void findByUUID(){

    }


}
