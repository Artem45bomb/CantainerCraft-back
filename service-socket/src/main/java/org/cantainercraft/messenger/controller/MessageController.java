package org.cantainercraft.messenger.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cantainercraft.messenger.dto.MessageDTO;
import org.cantainercraft.messenger.service.impl.MessageServiceImpl;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.util.UUID;

@Controller
@Slf4j
@RequiredArgsConstructor
public class MessageController {
    private final MessageServiceImpl messageService;

    @MessageMapping("/message/add")
    @SendTo("/topic/message/add")
    private MessageDTO submitMessage(@Payload MessageDTO message) throws Exception{

        UUID uuid = UUID.randomUUID();
        message.setClientId(uuid);
        messageService.asyncSave(message).subscribe(System.out::println);

        return message;
    }

    @MessageMapping("/message/delete")
    @SendTo("/topic/message/delete")
    private UUID deleteMessage(@Payload UUID clientId) throws Exception {

        messageService.deleteByClientId(clientId).subscribe(System.out::println);
        return clientId;
    }

    @MessageMapping("/message/update")
    @SendTo("/topic/message/update")
    private MessageDTO updateMessage(@Payload MessageDTO messageDTO) throws Exception{

        messageService.update(messageDTO).subscribe(System.out::println);

        return messageDTO;
    }

}
