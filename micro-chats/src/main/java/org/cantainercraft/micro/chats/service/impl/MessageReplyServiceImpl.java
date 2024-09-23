package org.cantainercraft.micro.chats.service.impl;

import lombok.RequiredArgsConstructor;
import org.cantainercraft.micro.chats.convertor.MessageReplyDTOConvertor;
import org.cantainercraft.micro.chats.dto.MessageDTO;
import org.cantainercraft.micro.chats.dto.MessageReplyDTO;
import org.cantainercraft.micro.chats.service.MessageReplyService;
import org.cantainercraft.micro.chats.service.MessageService;
import org.cantainercraft.micro.utilits.exception.NotResourceException;
import org.cantainercraft.project.entity.chats.Message;
import org.cantainercraft.project.entity.chats.Message_Reply;
import org.cantainercraft.micro.chats.repository.MessageReplyRepository;
import org.cantainercraft.project.entity.chats.TypeMessage;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MessageReplyServiceImpl implements MessageReplyService {
    private final MessageService messageService;
    private final MessageReplyRepository repository;
    private final MessageReplyDTOConvertor convertor;

    @Override
    public Message_Reply save(MessageReplyDTO dto) {
        Optional<Message> messageReply = messageService.findByUuid(dto.getMessageReply().getUuid());

        if(messageReply.isEmpty())
            throw new NotResourceException("message reply is not exist");


        MessageDTO message = dto.getMessage();
        message.setType(TypeMessage.REPLY);
        
        return repository.save(new Message_Reply(null,messageService.save(message),messageReply.get()));
    }

    @Override
    public Message_Reply update(MessageReplyDTO dto) {
        if (!repository.existsById(dto.getUuid())) {
            throw new NotResourceException("no Message_Reply by id");
        }
        Message_Reply entity = convertor.convertDTOToEntity(dto);
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
