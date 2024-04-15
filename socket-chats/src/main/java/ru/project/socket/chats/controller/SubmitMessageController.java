package ru.project.socket.chats.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.project.socket.chats.dto.MessageDTO;
import ru.project.socket.chats.feign.MessageFeignClient;
import ru.project.socket.chats.service.MessageService;

import java.util.UUID;

@Controller
@Slf4j
public class SubmitMessageController {
    private final MessageService messageService;
    private final MessageFeignClient messageFeignClient;

    public SubmitMessageController(MessageService messageService, MessageFeignClient messageFeignClient) {
        this.messageService = messageService;
        this.messageFeignClient = messageFeignClient;
    }


    @MessageMapping("/submit")
    @SendTo("/topic/public")
    private MessageDTO submitMessage(@Payload MessageDTO submitMessage) throws Exception{

        UUID uuid = UUID.randomUUID();
        submitMessage.setUuid(uuid);
        messageService.asyncSave(submitMessage).subscribe(System.out::println);

        return submitMessage;
    }

    @MessageMapping("/submit/delete")
    @SendTo("/topic/public")
    private UUID deleteMessage(@Payload UUID uuid) throws Exception {

        messageService.delete(uuid).subscribe(System.out::println);
        return uuid;
    }

    @MessageMapping("/submit/update")
    @SendTo("/topic/public")
    private MessageDTO updateMessage(@Payload MessageDTO messageDTO) throws Exception{

        messageService.update(messageDTO).subscribe(System.out::println);

        return messageDTO;
    }

}
