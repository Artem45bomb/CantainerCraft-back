package org.cantainercraft.micro.chats.service;

import org.cantainercraft.micro.chats.dto.MessageReadDTO;
import org.cantainercraft.project.entity.chats.MessageRead;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MessageReadService {

    MessageRead save(MessageReadDTO messageReadDTO);

    boolean update(MessageReadDTO messageReadDTO);

    boolean delete(UUID uuid);

    List<MessageRead> findAll();

    Optional<MessageRead> findById(UUID uuid);
}
