package org.cantainercraft.messenger.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cantainercraft.messenger.dto.MessageDTO;
import org.cantainercraft.messenger.dto.MessageForwardDTO;
import org.cantainercraft.messenger.dto.MessageReplyDTO;
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
    public MessageDTO submitMessage(@Payload MessageDTO message) throws Exception{

        UUID uuid = UUID.randomUUID();
        message.setClientId(uuid);
        messageService.asyncSave(message).subscribe(System.out::println);

        return message;
    }

    @MessageMapping("/message/delete")
    @SendTo("/topic/message/delete")
    public UUID deleteMessage(@Payload UUID clientId) throws Exception {

        messageService.deleteByClientId(clientId).subscribe(System.out::println);
        return clientId;
    }

    @MessageMapping("/message/pinned")
    @SendTo("/topic/message/pinned")
    public MessageDTO updateMessage(@Payload MessageDTO dto) throws Exception{
        MessageDTO pinnedMessage = MessageDTO.builder()
                .isPinned(dto.getIsPinned())
                .clientId(dto.getClientId())
                .build();

        messageService.update(dto).subscribe(System.out::println);
        return pinnedMessage;
    }

    @MessageMapping("/message/answer")
    @SendTo("/topic/message/answer")
    public MessageReplyDTO answerMessage(MessageReplyDTO dto){
        //logic
        return dto;
    }

    @MessageMapping("/message/forward")
    @SendTo("/topic/message/forward")
    public MessageForwardDTO forwardMessage(MessageForwardDTO dto){
        //logic
        return dto;
    }

}
