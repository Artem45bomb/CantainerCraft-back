package org.cantainercraft.messenger.service;

import org.cantainercraft.messenger.dto.EmotionServiceDTO;

public interface EmotionService {
    void addEmotion(EmotionServiceDTO dto);

    void deleteEmotion(EmotionServiceDTO dto);
}
