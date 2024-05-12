package org.cantainercraft.micro.chats.service;

import org.cantainercraft.micro.chats.dto.EmotionDTO;
import org.cantainercraft.project.entity.chats.Emotion;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface EmotionService {

    Mono<Emotion> findByUnicode(String unicode);

    Mono<Void> deleteByUnicode(String unicode);

    Mono<Void> deleteById(UUID uuid);

    Mono<Emotion> save(EmotionDTO emotionDTO);

    Mono<Emotion> update(EmotionDTO emotionDTO);

    Flux<Emotion> findAll();

    Mono<Emotion> findById(UUID uuid);
}
