package org.cantainercraft.messenger.service;

import org.cantainercraft.messenger.dto.EmotionServiceDTO;
import org.cantainercraft.messenger.dto.UserEmotionDTO;

import java.util.UUID;

public interface EmotionService {
    EmotionServiceDTO addEmotion(EmotionServiceDTO dto);

    void deleteEmotion(UUID uuid);
}
