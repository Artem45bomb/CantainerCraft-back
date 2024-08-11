package org.cantainercraft.micro.chats.service;

import org.cantainercraft.micro.chats.repository.dto.MessageReadDTO;
import org.cantainercraft.project.entity.chats.MessageRead;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MessageReadService {

    MessageRead save(MessageReadDTO messageReadDTO);

    MessageRead update(MessageReadDTO messageReadDTO);

    void delete(UUID uuid);

    List<MessageRead> findAll();

    Optional<MessageRead> findById(UUID uuid);
}
