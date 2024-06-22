package org.cantainercraft.messenger.service;

import org.cantainercraft.messenger.dto.EmotionDTO;
import org.cantainercraft.messenger.dto.MessageDTO;
import org.cantainercraft.messenger.dto.MessageSearchDTO;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.UUID;

public interface MessageService {
    MessageDTO save(MessageDTO messageDTO);
    Flux<MessageDTO> asyncSave(MessageDTO messageDTO);

    Flux<Boolean> deleteByClientId(UUID clientId);

    Flux<MessageDTO> update(MessageDTO messageDTO);

    List<MessageDTO> findBySearch(MessageSearchDTO searchDTO);

    MessageDTO findByUUID(UUID uuid);
}
