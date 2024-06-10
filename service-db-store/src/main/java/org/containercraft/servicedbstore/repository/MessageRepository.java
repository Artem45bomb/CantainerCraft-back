package org.containercraft.servicedbstore.repository;

import org.containercraft.servicedbstore.entity.Message;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface MessageRepository extends ReactiveCrudRepository<Message, UUID> {
    Mono<Message> findByUuidOrClientId(UUID id,UUID clientId);


    Flux<Message> findByChatId(UUID chatId);
}
