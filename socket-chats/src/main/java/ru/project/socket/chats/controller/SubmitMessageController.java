package ru.project.socket.chats.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.project.socket.chats.dto.MessageDTO;
import ru.project.socket.chats.service.MessageService;

import java.util.UUID;

@Controller
@Slf4j
public class SubmitMessageController {
    private final MessageService messageService;

    public SubmitMessageController(MessageService messageService) {
        this.messageService = messageService;
    }


    @MessageMapping("/submit")
    @SendTo("/topic/public")
    private MessageDTO submitMessage(@Payload MessageDTO submitMessage) throws Exception{

        UUID uuid = UUID.randomUUID();
        submitMessage.setUuid(uuid);
        Flux<MessageDTO> messageDTOMono = messageService.save(submitMessage);

        return submitMessage;
    }
}
