package org.cantainercraft.micro.chats.service.impl;

import lombok.RequiredArgsConstructor;
import org.cantainercraft.micro.chats.convertor.MessageReadDTOConvertor;
import org.cantainercraft.micro.chats.repository.dto.MessageReadDTO;
import org.cantainercraft.micro.chats.service.MessageReadService;
import org.cantainercraft.project.entity.chats.MessageRead;
import org.cantainercraft.micro.chats.repository.MessageReadRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MessageReadServiceImpl implements MessageReadService {

    private final MessageReadRepository repository;
    private final MessageReadDTOConvertor convertor;

    @Override
    public MessageRead save(MessageReadDTO messageReadDTO) {
        MessageRead entity = convertor.convertMessageReadDTOToMessageRead(messageReadDTO);
        return repository.save(entity);
    }

    @Override
    public boolean update(MessageReadDTO messageReadDTO) {
        if (repository.existsById(messageReadDTO.getUuid())) {
            MessageRead entity = convertor.convertMessageReadDTOToMessageRead(messageReadDTO);
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
    public List<MessageRead> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<MessageRead> findById(UUID uuid) {
        return repository.findById(uuid);
    }
}
