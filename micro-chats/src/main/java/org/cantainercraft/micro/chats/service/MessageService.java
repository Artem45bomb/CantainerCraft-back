package org.cantainercraft.micro.chats.service;

import org.cantainercraft.micro.chats.dto.MessageDTO;
import org.cantainercraft.project.entity.chats.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MessageService {
    Message save(MessageDTO messageDTO);

    Message update(MessageDTO messageDTO);

    boolean delete(UUID uuid);

    void deleteByClientId(UUID clientId);


    List<Message> findAll();

    Optional<Message> findByUuid(UUID uuid);

    Optional<Message> findByUuidOrClientId(UUID uuid,UUID clientId);

    Page<Message> findBySearch(Date dateStart, Date dateEnd, String text, UUID uuid, Long userId, UUID chatId, Pageable pageable);

    List<Message> findByUserId(Long id);
}
