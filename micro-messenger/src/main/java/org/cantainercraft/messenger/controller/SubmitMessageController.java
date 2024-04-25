package org.cantainercraft.messenger.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cantainercraft.messenger.feign.MessageFeignClient;
import org.cantainercraft.messenger.service.MessageService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.cantainercraft.messenger.dto.MessageDTO;

import java.util.UUID;

@Controller
@Slf4j
@RequiredArgsConstructor
public class SubmitMessageController {
    private final MessageService messageService;
    private final MessageFeignClient messageFeignClient;

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
