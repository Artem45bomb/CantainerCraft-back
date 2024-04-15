package ru.micro.chats.data.service;

import org.springframework.stereotype.Service;
import ru.micro.chats.data.dto.MessageDTO;
import ru.micro.chats.data.dto.MessageDTOConvertor;
import ru.micro.chats.data.repository.MessageRepository;
import ru.weather.project.entity.chats.Message;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MessageService {
    private final MessageRepository messageRepository;
    private final MessageDTOConvertor messageDTOConvertor;
    public MessageService(MessageRepository messageRepository, MessageDTOConvertor messageDTOConvertor){
        this.messageRepository = messageRepository;
        this.messageDTOConvertor = messageDTOConvertor;
    }

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
