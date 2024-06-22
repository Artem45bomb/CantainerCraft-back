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

    @MessageMapping("/submit")
    @SendTo("/topic/public")
    private MessageDTO submitMessage(@Payload MessageDTO submitMessage) throws Exception{

        UUID uuid = UUID.randomUUID();
        submitMessage.setClientId(uuid);
        messageService.asyncSave(submitMessage).subscribe(System.out::println);

        return submitMessage;
    }

    @MessageMapping("/submit/delete")
    @SendTo("/topic/public")
    private UUID deleteMessage(@Payload UUID clientId) throws Exception {

        messageService.deleteByClientId(clientId).subscribe(System.out::println);
        return clientId;
    }

    @MessageMapping("/submit/update")
    @SendTo("/topic/public")
    private MessageDTO updateMessage(@Payload MessageDTO messageDTO) throws Exception{

        messageService.update(messageDTO).subscribe(System.out::println);

        return messageDTO;
    }

}
