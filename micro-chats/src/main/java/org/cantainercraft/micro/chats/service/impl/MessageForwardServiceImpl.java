package org.cantainercraft.micro.chats.service.impl;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.cantainercraft.micro.chats.convertor.MessageForwardDTOConvertor;
import org.cantainercraft.micro.chats.dto.MessageDTO;
import org.cantainercraft.micro.chats.dto.MessageForwardDTO;
import org.cantainercraft.micro.chats.service.MessageForwardService;
import org.cantainercraft.micro.chats.service.MessageService;
import org.cantainercraft.micro.utilits.exception.NotResourceException;
import org.cantainercraft.project.entity.chats.Message;
import org.cantainercraft.project.entity.chats.Message_Forward;
import org.cantainercraft.micro.chats.repository.MessageForwardRepository;
import org.cantainercraft.project.entity.chats.TypeMessage;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MessageForwardServiceImpl implements MessageForwardService {
    private final MessageService messageService;
    private final MessageForwardRepository repository;
    private final MessageForwardDTOConvertor convertor;

    @Override
    public Message_Forward save(MessageForwardDTO dto) {
        Optional<Message> messageFrom = messageService.findByUuid(dto.getMessageFrom().getUuid());

        if(messageFrom.isEmpty())
            throw new NotResourceException("message from is not exist");

        MessageDTO message = dto.getMessage();
        message.setType(TypeMessage.FORWARD);

        return repository.save(new Message_Forward(null,messageFrom.get(),messageService.save(message)));
    }

    @Override
    public Message_Forward update(MessageForwardDTO dto) {
        if (!repository.existsById(dto.getUuid())) {
           throw new NotResourceException("no Message_Forward by id");
        }
        Message_Forward entity = convertor.convertDTOToEntity(dto);
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
