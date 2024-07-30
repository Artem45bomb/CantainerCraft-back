package org.cantainercraft.messenger.controller;

import lombok.RequiredArgsConstructor;
import org.cantainercraft.messenger.dto.ChatSecuredDTO;
import org.cantainercraft.messenger.service.ChatSecuredService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class ChatSecuredController {
    private final ChatSecuredService service;

    @MessageMapping("/chat-secured/add")
    @SendTo("/topic//chat-secured/add")
    public ChatSecuredDTO add(ChatSecuredDTO dto){
        return service.save(dto);
    }

    @MessageMapping("/chat-secured/delete")
    @SendTo("/topic//chat-secured/delete")
    public ChatSecuredDTO delete(ChatSecuredDTO dto){
        service.delete(dto);
        return dto;
    }

    @MessageMapping("/chat-secured/delete/id")
    @SendTo("/topic//chat-secured/delete/id")
    public UUID deleteById(UUID uuid){
        service.deleteById(uuid);
        return uuid;
    }
}
