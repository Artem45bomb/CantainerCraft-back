package org.cantainercraft.micro.chats.service.impl;

import lombok.RequiredArgsConstructor;
import org.cantainercraft.micro.chats.convertor.MessageDTOConvertor;
import org.cantainercraft.micro.chats.repository.MessageRepository;
import org.cantainercraft.micro.chats.service.MessageService;
import org.springframework.stereotype.Service;
import org.cantainercraft.micro.chats.dto.MessageDTO;
import org.cantainercraft.project.entity.chats.Message;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {
    private final MessageRepository messageRepository;
    private final MessageDTOConvertor messageDTOConvertor;


    public Message save(MessageDTO messageDTO){
        Message message = messageDTOConvertor.convertMessageDTOToMessage(messageDTO);
        return messageRepository.save(message);
    }

    public Message update(MessageDTO messageDTO){
        Message message = messageDTOConvertor.convertMessageDTOToMessage(messageDTO);
        return messageRepository.save(message);
    }

    public boolean delete(UUID uuid){
        messageRepository.deleteById(uuid);
        return true;
    }

    public List<Message> findAll(){
        return messageRepository.findAll();
    }

    public Optional<Message> findByUUID(UUID uuid){
        return messageRepository.findById(uuid);
    }

    public List<Message> findBySearch(Date dateStart, Date dateEnd, String value, UUID uuid, Long userId){
        return messageRepository.findBySearch(dateStart,dateEnd,value,uuid,userId);
    }
}
