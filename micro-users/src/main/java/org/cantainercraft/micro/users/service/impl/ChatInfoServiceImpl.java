package org.cantainercraft.micro.users.service.impl;


import lombok.RequiredArgsConstructor;
import org.cantainercraft.micro.users.convertor.ChatInfoDTOConvertor;
import org.cantainercraft.micro.users.dto.ChatInfoDTO;
import org.cantainercraft.micro.users.repository.ChatInfoRepository;
import org.cantainercraft.micro.users.service.ChatInfoService;
import org.cantainercraft.micro.utilits.exception.ExistResourceException;
import org.cantainercraft.micro.utilits.exception.NotResourceException;
import org.cantainercraft.project.entity.users.Chat_Info;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ChatInfoServiceImpl implements ChatInfoService {
    private final ChatInfoRepository repository;
    private final ChatInfoDTOConvertor convertor;

    @Override
    public Chat_Info save(ChatInfoDTO dto) {
        Chat_Info entity = convertor.convertDTOToEntity(dto);

        if (repository.existsByUserIdAndChatId(dto.getUser().getId(),dto.getChatId())){
            throw new ExistResourceException("Chat Info is exist");
        }

        return repository.save(entity);
    }

    @Override
    @CachePut(value = "chat-info",key = "#dto.uuid")
    public Chat_Info update(ChatInfoDTO dto) {
        Chat_Info entity = convertor.convertDTOToEntity(dto);

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
