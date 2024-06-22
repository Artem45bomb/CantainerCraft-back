package org.cantainercraft.micro.chats.service.impl;

import lombok.RequiredArgsConstructor;
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

@Service
public class MessageServiceImpl implements MessageService {
    private final MessageRepository messageRepository;
    private final MessageDTOConvertor messageDTOConvertor;
    private final StreamBridge template;
    private final ModelMapper mapper;
    private final EmotionRepository emotionRepository;

    public MessageServiceImpl(MessageRepository messageRepository,
                              MessageDTOConvertor messageDTOConvertor,
                              StreamBridge template,
                              @Qualifier("stream-mapper-message") ModelMapper mapper, EmotionRepository emotionRepository) {
        this.messageRepository = messageRepository;
        this.messageDTOConvertor = messageDTOConvertor;
        this.template = template;
        this.mapper = mapper;
        this.emotionRepository = emotionRepository;
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

    public void deleteByClientId(UUID clientId){
        Optional<Message> message = messageRepository.findByClientId(clientId);

        if(message.isEmpty()){
            throw new NotResourceException("message is not exist");
        }
        messageRepository.deleteByClientId(clientId);
    }


    @Override
    public Message addEmotion(MessageEmotionDTO dto) {
        Optional<Message> message = messageRepository.findByClientId(dto.getMessageClientId());
        Optional<Emotion> emotion = emotionRepository.findById(dto.getEmotionId());

        return null;
    }

    @Override
    public Message deleteEmotion(MessageEmotionDTO dto) {
        Optional<Message> message = messageRepository.findByClientId(dto.getMessageClientId());
        Optional<Emotion> emotion = emotionRepository.findById(dto.getEmotionId());
        return null;
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
