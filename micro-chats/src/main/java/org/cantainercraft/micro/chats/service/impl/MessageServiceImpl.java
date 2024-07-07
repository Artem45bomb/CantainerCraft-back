package org.cantainercraft.micro.chats.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cantainercraft.micro.chats.convertor.MessageDTOConvertor;
import org.cantainercraft.micro.chats.dto.stream.MessageChannelDTO;
import org.cantainercraft.micro.chats.repository.EmotionRepository;
import org.cantainercraft.micro.chats.repository.MessageRepository;
import org.cantainercraft.micro.chats.service.MessageService;
import org.cantainercraft.micro.utilits.exception.NotResourceException;
import org.cantainercraft.project.entity.chats.Emotion;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.cantainercraft.micro.chats.dto.MessageDTO;
import org.cantainercraft.project.entity.chats.Message;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {
    private final MessageRepository messageRepository;
    private final MessageDTOConvertor messageDTOConvertor;

    @Override
    public Message save(MessageDTO messageDTO) {
        Message message = messageDTOConvertor.convertMessageDTOToMessage(messageDTO);


        return messageRepository.save(message);
    }

    @Override
    public Message update(MessageDTO messageDTO){
        Message message = messageDTOConvertor.convertMessageDTOToMessage(messageDTO);
        return messageRepository.save(message);
    }

    @Override
    public boolean delete(UUID uuid){
        messageRepository.deleteById(uuid);
        return true;
    }

    @Override
    public void deleteByClientId(UUID clientId){
        Optional<Message> message = messageRepository.findByClientId(clientId);

        if(message.isEmpty()){
            throw new NotResourceException("message is not exist");
        }
        messageRepository.deleteByClientId(clientId);
    }

    @Override
    public List<Message> findAll(){
        return messageRepository.findAll();
    }

    @Override
    public Optional<Message> findByUuid(UUID uuid){
        return messageRepository.findById(uuid);
    }

    @Override
    public Optional<Message> findByUuidOrClientId(UUID uuid,UUID clientId){
        return messageRepository.findByUuidOrClientId(uuid,clientId);
    }

    @Override
    public Page<Message> findBySearch(Date dateStart, Date dateEnd, String text, UUID uuid, Long userId, UUID chatId, Pageable pageable){
        return messageRepository.findBySearch(dateStart,dateEnd,text,uuid,userId,chatId,pageable);
    }

    @Override
    public List<Message> findByUserId(Long id) {
        return messageRepository.findByUserId(id);
    }


}
