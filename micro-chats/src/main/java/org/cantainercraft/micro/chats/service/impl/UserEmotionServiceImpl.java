package org.cantainercraft.micro.chats.service.impl;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cantainercraft.micro.chats.convertor.UserEmotionDTOConvertor;
import org.cantainercraft.micro.chats.dto.EmotionAddDTO;
import org.cantainercraft.micro.chats.dto.EmotionDeleteDTO;
import org.cantainercraft.micro.chats.dto.UserEmotionDTO;
import org.cantainercraft.micro.chats.repository.EmotionRepository;
import org.cantainercraft.micro.chats.repository.MessageRepository;
import org.cantainercraft.micro.chats.repository.UserEmotionRepository;
import org.cantainercraft.micro.chats.service.UserEmotionService;
import org.cantainercraft.micro.chats.webflux.UserWebClient;
import org.cantainercraft.micro.utilits.exception.NotResourceException;
import org.cantainercraft.micro.utilits.exception.NotValidateParamException;
import org.cantainercraft.project.entity.chats.Emotion;
import org.cantainercraft.project.entity.chats.Message;
import org.cantainercraft.project.entity.chats.User_Emotion;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserEmotionServiceImpl implements UserEmotionService {
    private final UserEmotionRepository repository;
    private final UserEmotionDTOConvertor convertor;
    private final MessageRepository messageRepository;
    private final EmotionRepository emotionRepository;
    private final UserWebClient webClient;

    @Override
    public User_Emotion save(UserEmotionDTO dto) {
        
        if(dto.getUuid() != null) throw new NotValidateParamException("missed param:id");

        if(!webClient.userExist(dto.getUserId())) throw new NotResourceException("user is not exist");

        User_Emotion entity = convertor.convertDTOToEntity(dto);
        return repository.save(entity);
    }

    @Override
    public User_Emotion update(UserEmotionDTO dto) {
        if (!repository.existsById(dto.getUuid())) {
            throw new NotResourceException("Not exist");
        }
        
        if(!webClient.userExist(dto.getUserId())){
            throw new NotResourceException("user is not exist");
        }
        
        User_Emotion entity = convertor.convertDTOToEntity(dto);
        return repository.save(entity);

    }

    @Override
    public void delete(UUID uuid) {
        if(!repository.existsById(uuid)) {
            throw new NotResourceException("not is exist");
        }

        repository.deleteById(uuid);
    }

    @Override
    public List<User_Emotion> findAll() {
        return repository.findAll();
    }

    @Override
    public User_Emotion findById(UUID uuid) {
        Optional<User_Emotion> entity = repository.findById(uuid);
        
        if(entity.isEmpty()) throw new NotResourceException("not is exist");
        
        return entity.get();
    }

    @Override
    public  User_Emotion addEmotion(EmotionAddDTO dto){
        Optional<Message> message = messageRepository.findByClientId(dto.getMessageClientId());
        Optional<Emotion> emotion = emotionRepository.findById(dto.getEmotionId());

        if(message.isEmpty()) throw new NotResourceException("message is not exist");
        if(emotion.isEmpty()) throw new NotResourceException("emotion is not exist");
        if(!webClient.userExist(dto.getUserId())) throw new NotResourceException("user is not exist");


        return repository.save(User_Emotion.builder()
                .userId(dto.getUserId())
                .emotion(emotion.get())
                .message(message.get())
                .build());

    }

    @Override
    public void deleteEmotion(EmotionDeleteDTO dto){
        Optional<User_Emotion> userEmotion = repository.findById(dto.getUuid());

        if(userEmotion.isEmpty()) throw new NotResourceException("this user not add emotion");

        repository.deleteById(dto.getUuid());
    }
}
