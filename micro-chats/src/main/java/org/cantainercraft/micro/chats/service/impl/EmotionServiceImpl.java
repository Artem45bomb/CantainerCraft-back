
package org.cantainercraft.micro.chats.service.impl;

import lombok.RequiredArgsConstructor;
import org.cantainercraft.micro.chats.convertor.EmotionDTOConvertor;
import org.cantainercraft.micro.chats.repository.dto.EmotionDTO;
import org.cantainercraft.micro.chats.repository.EmotionRepository;
import org.cantainercraft.micro.chats.service.EmotionService;
import org.cantainercraft.micro.utilits.exception.ExistResourceException;
import org.cantainercraft.micro.utilits.exception.NotResourceException;
import org.cantainercraft.project.entity.chats.Emotion;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class EmotionServiceImpl implements EmotionService {
    private final EmotionRepository repository;
    private final EmotionDTOConvertor convertor;

    public Optional<Emotion> findByUnicode(String unicode) {
        return repository
                .findByUnicode(unicode);
    }

    public void deleteByUnicode(String unicode) {
        repository
                .findByUnicode(unicode)
                .orElseThrow(() -> {throw new NotResourceException("emotion is not exist");});
        repository.deleteByUnicode(unicode);
    }

    public void deleteById(UUID uuid) {
        repository
                .findById(uuid)
                .orElseThrow(() ->{throw new NotResourceException("");});
        repository.deleteById(uuid);
    }

    public Emotion save(EmotionDTO emotionDTO) {
        Optional<Emotion> emotion = repository
                .findByUnicode(emotionDTO.getUnicode());

        if(emotion.isEmpty()){
            return repository
                    .save(convertor.convertDTOToEntity(emotionDTO));
        }
        else
            throw new ExistResourceException("emotion is exist");
    }

    public Emotion update(EmotionDTO emotionDTO) {
        repository
                .findById(emotionDTO.getUuid())
                .orElseThrow(() -> {throw new NotResourceException("emotion is not exist");});
        return repository
                .save(convertor.convertDTOToEntity(emotionDTO));
    }
    public List<Emotion> findAll() {
        return repository.findAll();
    }

    public Optional<Emotion> findById(UUID uuid){
        return repository.findById(uuid);
    }

}

