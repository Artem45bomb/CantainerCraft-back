package org.cantainercraft.messenger.service.impl;

import lombok.RequiredArgsConstructor;
import org.cantainercraft.messenger.dto.EmotionServiceDTO;
import org.cantainercraft.messenger.service.EmotionService;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class EmotionServiceImpl implements EmotionService {
    private final WebClient webClient;

    @Override
    public void addEmotion(EmotionServiceDTO dto) {

    }

    @Override
    public void deleteEmotion(EmotionServiceDTO dto) {

    }
}
