package org.cantainercraft.micro.chats.service;

import org.cantainercraft.micro.chats.dto.MessageReplyDTO;
import org.cantainercraft.project.entity.chats.Message_Reply;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MessageReplyService {

    Message_Reply save(MessageReplyDTO messageReplyDTO);

    boolean update(MessageReplyDTO messageReplyDTO);

    boolean delete(UUID uuid);

    List<Message_Reply> findAll();

    Optional<Message_Reply> findById(UUID uuid);

    void deleteByMessageReplyUserId(Long userId);

    Optional<Message_Reply> findByMessageReplyUserId(Long userId);
}
