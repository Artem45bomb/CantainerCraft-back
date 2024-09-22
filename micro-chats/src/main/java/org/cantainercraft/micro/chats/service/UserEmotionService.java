package org.cantainercraft.micro.chats.service;


import org.cantainercraft.micro.chats.dto.EmotionAddDTO;
import org.cantainercraft.micro.chats.dto.EmotionDeleteDTO;
import org.cantainercraft.micro.chats.dto.UserEmotionDTO;
import org.cantainercraft.project.entity.chats.User_Emotion;

import java.util.List;
import java.util.UUID;

public interface UserEmotionService {

    User_Emotion save(UserEmotionDTO dto);
    void delete(UUID uuid);

    User_Emotion findById(UUID uuid);

    List<User_Emotion> findAll();

    User_Emotion addEmotion(EmotionAddDTO dto);

    void deleteEmotion(EmotionDeleteDTO dto);

}
