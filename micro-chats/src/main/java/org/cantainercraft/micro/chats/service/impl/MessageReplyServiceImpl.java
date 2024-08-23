package org.cantainercraft.micro.chats.service.impl;

import lombok.RequiredArgsConstructor;
import org.cantainercraft.micro.chats.convertor.MessageReplyDTOConvertor;
import org.cantainercraft.micro.chats.dto.MessageReplyDTO;
import org.cantainercraft.micro.chats.service.MessageReplyService;
import org.cantainercraft.micro.utilits.exception.NotResourceException;
import org.cantainercraft.project.entity.chats.Message_Reply;
import org.cantainercraft.micro.chats.repository.MessageReplyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MessageReplyServiceImpl implements MessageReplyService {

    private final MessageReplyRepository repository;
    private final MessageReplyDTOConvertor convertor;

    @Override
    public Message_Reply save(MessageReplyDTO messageReplyDTO) {
        Message_Reply entity = convertor.convertDTOToEntity(messageReplyDTO);
        return repository.save(entity);
    }

    @Override
    public Message_Reply update(MessageReplyDTO messageReplyDTO) {
        if (!repository.existsById(messageReplyDTO.getUuid())) {
            throw new NotResourceException("no Message_Reply by id");
        }
        Message_Reply entity = convertor.convertDTOToEntity(messageReplyDTO);
        return repository.save(entity);
    }

    @Override
    public void delete(UUID uuid) {
        if (!repository.existsById(uuid)) {
           throw new NotResourceException("no Message_Reply by id");
        }
        repository.deleteById(uuid);
    }

    @Override
    public List<Message_Reply> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Message_Reply> findById(UUID uuid) {
        return repository.findById(uuid);
    }
}
