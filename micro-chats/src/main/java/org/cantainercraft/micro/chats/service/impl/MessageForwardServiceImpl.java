package org.cantainercraft.micro.chats.service.impl;

import lombok.RequiredArgsConstructor;
import org.cantainercraft.micro.chats.convertor.MessageForwardDTOConvertor;
import org.cantainercraft.micro.chats.repository.dto.MessageForwardDTO;
import org.cantainercraft.micro.chats.service.MessageForwardService;
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
        Message_Forward entity = convertor.convertMessageForwardDTOToMessageForward(messageForwardDTO);
        return repository.save(entity);
    }

    @Override
    public boolean update(MessageForwardDTO messageForwardDTO) {
        if (repository.existsById(messageForwardDTO.getUuid())) {
            Message_Forward entity = convertor.convertMessageForwardDTOToMessageForward(messageForwardDTO);
            repository.save(entity);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(UUID uuid) {
        if (repository.existsById(uuid)) {
            repository.deleteById(uuid);
            return true;
        }
        return false;
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
