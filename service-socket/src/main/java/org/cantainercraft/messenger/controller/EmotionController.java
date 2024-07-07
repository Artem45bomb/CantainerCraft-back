package org.cantainercraft.messenger.controller;

import lombok.RequiredArgsConstructor;
import org.cantainercraft.messenger.dto.EmotionServiceDTO;
import org.cantainercraft.messenger.service.EmotionService;
import org.cantainercraft.messenger.webclient.EmotionClient;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class EmotionController {
    private final EmotionService emotionService;


    @MessageMapping("/emotion/add")
    @SendTo("/topic/emotion/add")
    public EmotionServiceDTO addEmotion(@Payload EmotionServiceDTO dto){
        return emotionService.addEmotion(dto);
    }

    @MessageMapping("/emotion/delete")
    @SendTo("/topic/emotion/delete")
    public UUID deleteEmotion(@Payload UUID uuid){

        emotionService.deleteEmotion(uuid);

        return uuid;
    }
}
