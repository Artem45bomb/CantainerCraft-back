package org.cantainercraft.micro.chats.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.cantainercraft.micro.chats.convertor.MessageDTOConvertor;
import org.cantainercraft.micro.chats.dto.stream.MessageChannelDTO;
import org.cantainercraft.micro.chats.repository.MessageRepository;
import org.cantainercraft.micro.chats.service.MessageService;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
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

@Service
public class MessageServiceImpl implements MessageService {
    private final MessageRepository messageRepository;
    private final MessageDTOConvertor messageDTOConvertor;
    private final StreamBridge template;
    private final ModelMapper mapper;

    public MessageServiceImpl(MessageRepository messageRepository,
                              MessageDTOConvertor messageDTOConvertor,
                              StreamBridge template,
                              @Qualifier("stream-mapper-message") ModelMapper mapper) {
        this.messageRepository = messageRepository;
        this.messageDTOConvertor = messageDTOConvertor;
        this.template = template;
        this.mapper = mapper;
    }


    public Message save(MessageDTO messageDTO) {
        Message message = messageDTOConvertor.convertMessageDTOToMessage(messageDTO);

        var sendMessage = MessageBuilder.withPayload(mapper.map(messageDTO, MessageChannelDTO.class)).build();
        template.send("submitMessage-out-0",sendMessage);

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

    public Page<Message> findBySearch(Date dateStart, Date dateEnd, String text, UUID uuid, Long userId, UUID chatId, Pageable pageable){
        return messageRepository.findBySearch(dateStart,dateEnd,text,uuid,userId,chatId,pageable);
    }

    public List<Message> findByUserId(Long id) {
        return messageRepository.findByUserId(id);
    }
}
