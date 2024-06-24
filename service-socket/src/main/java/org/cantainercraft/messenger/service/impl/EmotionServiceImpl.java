package org.cantainercraft.messenger.service.impl;

import lombok.RequiredArgsConstructor;
import org.cantainercraft.messenger.dto.EmotionServiceDTO;
import org.cantainercraft.messenger.dto.UserEmotionDTO;
import org.cantainercraft.messenger.service.EmotionService;
import org.cantainercraft.messenger.webclient.EmotionClient;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EmotionServiceImpl implements EmotionService {
    private final EmotionClient webClient;

    @Override
    public EmotionServiceDTO addEmotion(EmotionServiceDTO dto) {
        return webClient.addEmotion(dto);
    }

    @Override
    public void deleteEmotion(UUID uuid) {
        webClient.deleteEmotion(uuid);
    }
}
