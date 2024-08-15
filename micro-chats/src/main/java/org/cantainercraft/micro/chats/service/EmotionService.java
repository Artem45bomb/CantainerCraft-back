package org.cantainercraft.micro.chats.service;

import org.cantainercraft.micro.chats.dto.EmotionDTO;
import org.cantainercraft.project.entity.chats.Emotion;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EmotionService {

    Optional<Emotion> findByUnicode(String unicode);

    void deleteByUnicode(String unicode);

    void deleteById(UUID uuid);

    Emotion save(EmotionDTO emotionDTO);

    Emotion update(EmotionDTO emotionDTO);

    List<Emotion> findAll();

    Optional<Emotion> findById(UUID uuid);
}
