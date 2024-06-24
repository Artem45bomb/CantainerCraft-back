package org.cantainercraft.messenger.webclient;

import lombok.RequiredArgsConstructor;
import org.cantainercraft.messenger.dto.EmotionDTO;
import org.cantainercraft.messenger.dto.EmotionServiceDTO;
import org.cantainercraft.messenger.dto.UserEmotionDTO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class EmotionClient {
    private final WebClient webClient;

    public EmotionServiceDTO addEmotion(EmotionServiceDTO dto){
        return webClient
                .post()
                .uri("/user_emotion/message/add")
                .bodyValue(dto)
                .retrieve()
                .bodyToMono(EmotionServiceDTO.class)
                .block();
    }

    public void deleteEmotion(UUID uuid){
        webClient
                .put()
                .uri("/user_emotion/message/delete")
                .bodyValue(uuid)
                .retrieve()
                .bodyToMono(Void.class)
                .block();
    }
}
