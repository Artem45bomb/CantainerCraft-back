package org.cantainercraft.micro.users.service.impl;


import lombok.RequiredArgsConstructor;
import org.cantainercraft.micro.users.convertor.ChatInfoDTOConvertor;
import org.cantainercraft.micro.users.dto.ChatInfoDTO;
import org.cantainercraft.micro.users.repository.ChatInfoRepository;
import org.cantainercraft.micro.users.service.ChatInfoService;
import org.cantainercraft.micro.utilits.exception.NotResourceException;
import org.cantainercraft.project.entity.users.Chat_Info;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ChatInfoServiceImpl implements ChatInfoService {
    private final ChatInfoRepository repository;
    private final ChatInfoDTOConvertor dtoConvertor;

    @Override
    public Chat_Info save(ChatInfoDTO dto) {
        Chat_Info entity = dtoConvertor.convertDTOToEntity(dto);
        if (!repository.existsById(dto.getUuid())) {
            throw new NotResourceException("Chat Info not found");
        }

        return repository.save(entity);
    }

    @Override
    @CachePut(value = "chat-info",key = "#dto.uuid")
    public Chat_Info update(ChatInfoDTO dto) {
        Chat_Info entity = dtoConvertor.convertDTOToEntity(dto);
        if (!repository.existsById(dto.getUuid())) {
            throw new NotResourceException("Chat Info not found");
        }

        return repository.save(entity);
    }

    @Override
    @Cacheable(value = "chat-info",key = "#uuid")
    public Chat_Info findById(UUID uuid) {
        Optional<Chat_Info> chatInfo = repository.findById(uuid);
        if (chatInfo.isEmpty()) {
            throw new NotResourceException("No such chat info");
        }

        return chatInfo.get();
    }


    @Override
    public List<Chat_Info> findAll() {
        return repository.findAll();
    }

    @Override
    @CacheEvict(value = "chat-info",key = "#uuid")
    public void deleteById(UUID uuid) {
        if (!repository.existsById(uuid)){
            throw new NotResourceException("Chat Info not found");
        }
        repository.deleteById(uuid);
    }

}
