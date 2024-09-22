
package org.cantainercraft.micro.chats.service.impl;

import lombok.RequiredArgsConstructor;
import org.cantainercraft.micro.chats.convertor.EmotionDTOConvertor;
import org.cantainercraft.micro.chats.dto.EmotionDTO;
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

    @Override
    public Optional<Emotion> findByUnicode(String unicode) {
        return repository
                .findByUnicode(unicode);
    }

    @Override
    public void deleteByUnicode(String unicode) {
        if(!repository.existsByUnicode(unicode)) 
            throw new NotResourceException("emotion is not exist");
        
        repository.deleteByUnicode(unicode);
    }

    @Override
    public void deleteById(UUID uuid) {
        if(!repository.existsById(uuid))
            throw new NotResourceException("emotion is not exist");
        
        repository.deleteById(uuid);
    }

    @Override
    public Emotion save(EmotionDTO dto) {
        if(repository.existsByUnicode(dto.getUnicode()))
            throw new ExistResourceException("emotion is exist");

        return repository.save(convertor.convertDTOToEntity(dto));
    }

    @Override
    public Emotion update(EmotionDTO dto) {
        Optional<Emotion> emotion = repository.findById(dto.getUuid());
        Optional<Emotion> findByUnicode = repository.findByUnicode(dto.getUnicode());

        if(emotion.isEmpty())
            throw new NotResourceException("emotion is not exist");

        if(findByUnicode.isPresent() && !findByUnicode.get().getUuid().equals(dto.getUuid()))
            throw new NotResourceException("emotion is not exist");

        return repository
                .save(convertor.convertDTOToEntity(dto));
    }
    
    @Override
    public List<Emotion> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Emotion> findById(UUID uuid){
        return repository.findById(uuid);
    }

}

