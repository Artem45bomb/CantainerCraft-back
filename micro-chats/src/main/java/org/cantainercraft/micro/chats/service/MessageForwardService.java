package org.cantainercraft.micro.chats.service;

import org.cantainercraft.micro.chats.repository.dto.MessageForwardDTO;
import org.cantainercraft.project.entity.chats.Message_Forward;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MessageForwardService {

    Message_Forward save(MessageForwardDTO messageForwardDTO);

    Message_Forward update(MessageForwardDTO messageForwardDTO);

    void delete(UUID uuid);

    List<Message_Forward> findAll();

    Optional<Message_Forward> findById(UUID uuid);
}
