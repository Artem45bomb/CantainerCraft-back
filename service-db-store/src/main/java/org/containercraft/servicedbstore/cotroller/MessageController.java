package org.containercraft.servicedbstore.cotroller;

import lombok.RequiredArgsConstructor;
import org.containercraft.servicedbstore.dto.MessageDTO;
import org.containercraft.servicedbstore.entity.Message;
import org.containercraft.servicedbstore.service.MessageService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping("/messages")
@RequiredArgsConstructor
public class MessageController {
    private final MessageService service;


    @GetMapping("/all")
    public Flux<Message> findAll(){
        return service.findAll();
    }

    @GetMapping("/{uuid}")
    public Mono<Message> findById(@PathVariable UUID uuid){
        return service.findById(uuid);
    }

    @GetMapping("/chat/{chatId}")
    public Flux<Message> findByChatId(@PathVariable UUID chatId){
        return service.findByChatId(chatId);
    }
}
