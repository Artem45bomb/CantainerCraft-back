package ru.project.socket.chats.service;

import org.springframework.stereotype.Service;
import ru.project.socket.chats.convertor.MessageDTOConvertor;
import ru.project.socket.chats.dto.MessageDTO;
import ru.project.socket.chats.dto.MessageSearchDTO;
import ru.project.socket.chats.dto.MessageUpdateDTO;
import ru.project.socket.chats.repository.MessageRepository;
import ru.weather.project.entity.Message;

import java.util.Date;
import java.util.List;
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

    public boolean update(MessageUpdateDTO messageUpdateDTO){
        Message message = messageDTOConvertor.convertMessageDTOToMessage(messageUpdateDTO);
        message.setUuid(messageUpdateDTO.getUuid());
        messageRepository.save(message);
        return true;
    }

    public boolean delete(UUID uuid){
        messageRepository.deleteById(uuid);
        return true;
    }

    public List<Message> findAll(){
        return messageRepository.findAll();
    }

    public Message findByUUID(UUID uuid){
        return messageRepository.findById(uuid).get();
    }

    public List<Message> findBySearch(Date dateStart,Date dateEnd,String value,UUID uuid,Long userId){
        return messageRepository.findBySearch(dateStart,dateEnd,value,uuid,userId);
    }
}
