package org.cantainercraft.micro.chats.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.cantainercraft.micro.chats.convertor.MessageReadDTOConvertor;
import org.cantainercraft.micro.chats.dto.MessageReadDTO;
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
        MessageRead entity = convertor.convertDTOToEntity(messageReadDTO);
        return repository.save(entity);
    }

    @Override
    public MessageRead update(MessageReadDTO messageReadDTO) {
        if (!repository.existsById(messageReadDTO.getUuid())) {
           throw new EntityNotFoundException("Message read with uuid " + messageReadDTO.getUuid() + " not found");
        }
        MessageRead entity = convertor.convertDTOToEntity(messageReadDTO);
        return repository.save(entity);
    }

    @Override
    public void delete(UUID uuid) {
        if (!repository.existsById(uuid)) {
           throw new EntityNotFoundException("Message read with uuid " + uuid + " not found");
        } 
        repository.deleteById(uuid);
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
