package org.cantainercraft.micro.chats.service.impl;

import lombok.RequiredArgsConstructor;
import org.cantainercraft.micro.chats.convertor.MessageForwardDTOConvertor;
import org.cantainercraft.micro.chats.dto.MessageForwardDTO;
import org.cantainercraft.micro.chats.service.MessageForwardService;
import org.cantainercraft.micro.utilits.exception.NotResourceException;
import org.cantainercraft.project.entity.chats.Message_Forward;
import org.cantainercraft.micro.chats.repository.MessageForwardRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MessageForwardServiceImpl implements MessageForwardService {

    private final MessageForwardRepository repository;
    private final MessageForwardDTOConvertor convertor;

    @Override
    public Message_Forward save(MessageForwardDTO messageForwardDTO) {
        Message_Forward entity = convertor.convertDTOToEntity(messageForwardDTO);

        return repository.save(entity);
    }

    @Override
    public Message_Forward update(MessageForwardDTO messageForwardDTO) {
        if (!repository.existsById(messageForwardDTO.getUuid())) {
           throw new NotResourceException("no Message_Forward by id");
        }
        Message_Forward entity = convertor.convertDTOToEntity(messageForwardDTO);
        return repository.save(entity);
    }

    @Override
    public void delete(UUID uuid) {
        if(!repository.existsById(uuid)) {
            throw new NotResourceException("no Message_Forward by id");
        }
        repository.deleteById(uuid);
    }

    @Override
    public List<Message_Forward> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Message_Forward> findById(UUID uuid) {
        return repository.findById(uuid);
    }
}
