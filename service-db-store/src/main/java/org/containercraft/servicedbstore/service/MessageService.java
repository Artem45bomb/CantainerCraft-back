package org.containercraft.servicedbstore.service;

import org.containercraft.servicedbstore.dto.MessageDTO;
import org.containercraft.servicedbstore.entity.Message;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;

public interface MessageService {
    Flux<Message> findAll();

    Mono<Message> findById(UUID uuid);

    Mono<Message> findByIdOrClientId(UUID id,UUID clientId);

    Mono<Message> save(MessageDTO messageDTO);

    Mono<Message> update(MessageDTO messageDTO);

    Mono<Void> delete(UUID uuid);

    Flux<Message> findByChatId(UUID chatId);
}
