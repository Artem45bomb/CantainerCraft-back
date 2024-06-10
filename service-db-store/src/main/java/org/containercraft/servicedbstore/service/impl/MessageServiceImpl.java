package org.containercraft.servicedbstore.service.impl;

import lombok.RequiredArgsConstructor;
import org.cantainercraft.micro.utilits.service.ConvertorDTO;
import org.containercraft.servicedbstore.dto.MessageDTO;
import org.containercraft.servicedbstore.entity.Message;
import org.containercraft.servicedbstore.repository.MessageRepository;
import org.containercraft.servicedbstore.service.MessageService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {
    private final MessageRepository repository;
    private final ConvertorDTO<MessageDTO,Message> convertor;

    @Override
    public Flux<Message> findAll() {
        return repository.findAll();
    }

    @Override
    public Mono<Message> findById(UUID uuid) {
        return repository
                .findById(uuid)
                .flatMap(e -> {
                    if(e == null) return Mono.error(new Throwable("message is not exist"));
                    else return Mono.just(e);
                });
    }

    @Override
    public Mono<Message> findByIdOrClientId(UUID id, UUID clientId) {
        return repository.findByUuidOrClientId(id, clientId);
    }

    @Override
    public Mono<Message> save(MessageDTO messageDTO) {
        Message message = convertor.convertDTOToEntity(messageDTO);
        return repository
                .findById(messageDTO.getUuid())
                .flatMap(e -> {
                    if(e == null) {
                        return repository.save(message);
                    } else return Mono.empty();
                })
                 .switchIfEmpty(Mono.error(new Throwable("message is exist")));
    }

    @Override
    public Mono<Message> update(MessageDTO messageDTO) {
        Message message = convertor.convertDTOToEntity(messageDTO);

        return repository.save(message);
    }

    @Override
    public Mono<Void> delete(UUID uuid) {
        return repository.deleteById(uuid);
    }

    @Override
    public Flux<Message> findByChatId(UUID chatId){
        return repository.findByChatId(chatId);
    }

}
