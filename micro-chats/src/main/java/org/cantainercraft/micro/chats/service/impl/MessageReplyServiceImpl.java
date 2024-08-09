package org.cantainercraft.micro.chats.service.impl;

import lombok.RequiredArgsConstructor;
import org.cantainercraft.micro.chats.convertor.MessageReplyDTOConvertor;
import org.cantainercraft.micro.chats.repository.dto.MessageReplyDTO;
import org.cantainercraft.micro.chats.service.MessageReplyService;
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
        Message_Reply entity = convertor.convertMessageReplyDTOToMessageReply(messageReplyDTO);
        return repository.save(entity);
    }

    @Override
    public boolean update(MessageReplyDTO messageReplyDTO) {
        if (repository.existsById(messageReplyDTO.getUuid())) {
            Message_Reply entity = convertor.convertMessageReplyDTOToMessageReply(messageReplyDTO);
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
    public List<Message_Reply> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Message_Reply> findById(UUID uuid) {
        return repository.findById(uuid);
    }

    @Override
    public void deleteByMessageReplyUserId(Long userId) {
        repository.deleteByMessageReplyUserId(userId);
    }

    @Override
    public Optional<Message_Reply> findByMessageReplyUserId(Long userId) {
        return repository.findByMessageReplyUserId(userId);
    }
}
