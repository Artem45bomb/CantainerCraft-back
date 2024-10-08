package org.cantainercraft.micro.chats.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cantainercraft.micro.chats.convertor.MessageDTOConvertor;
import org.cantainercraft.micro.chats.repository.MessageRepository;
import org.cantainercraft.micro.chats.service.MessageService;
import org.cantainercraft.micro.utilits.exception.NotResourceException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    private final MessageRepository repository;
    private final MessageDTOConvertor convertor;

    @Override
    public Message save(MessageDTO messageDTO) {
        Message message = convertor.convertDTOToEntity(messageDTO);

        return repository.save(message);
    }

    @Override
    public Message update(MessageDTO messageDTO){
        Message message = convertor.convertDTOToEntity(messageDTO);

        if(!repository.existsById(messageDTO.getUuid()))
            throw new NotResourceException("Message with id " + messageDTO.getUuid() + "does not exist");

        return repository.save(message);
    }

    @Override
    public void delete(UUID uuid){
        if(!repository.existsById(uuid))
            throw new NotResourceException("Message with id " + uuid + " does not exist");

        repository.deleteById(uuid);
    }

    @Override
    public List<Message> findAll(){
        return repository.findAll();
    }

    @Override
    public Optional<Message> findByUuid(UUID uuid){
        return repository.findById(uuid);
    }

    @Override
    public Page<Message> findBySearch(Date dateStart, Date dateEnd, String text, UUID uuid, Long userId, UUID chatId, Pageable pageable){
        return repository.findBySearch(dateStart,dateEnd,text,uuid,userId,chatId,pageable);
    }

    @Override
    public List<Message> findByUserId(Long id) {
        return repository.findByUserId(id);
    }

    @Override
    public boolean existById(UUID id) {
        return repository.existsById(id);
    }

}
