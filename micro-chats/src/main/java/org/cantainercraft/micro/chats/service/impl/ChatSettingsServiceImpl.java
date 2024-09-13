package org.cantainercraft.micro.chats.service.impl;

import lombok.RequiredArgsConstructor;
import org.cantainercraft.micro.chats.convertor.ChatSettingsDTOConvertor;
import org.cantainercraft.micro.chats.dto.ChatSettingsDTO;
import org.cantainercraft.micro.chats.repository.ChatSettingsRepository;
import org.cantainercraft.micro.chats.service.ChatSettingsService;
import org.cantainercraft.micro.utilits.exception.NotResourceException;
import org.cantainercraft.project.entity.chats.Chat_Settings;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class ChatSettingsServiceImpl implements ChatSettingsService {
    private final ChatSettingsDTOConvertor convertor;
    private final ChatSettingsRepository repository;

    @Override
    public void delete(UUID uuid) {
        if(!repository.existsById(uuid))
            throw new NotResourceException("no settings by id");

        repository.deleteById(uuid);
    }


    @Override
    public Chat_Settings update(ChatSettingsDTO dto) {
        if(!repository.existsById(dto.getUuid()))
            throw new NotResourceException("no settings by id");

        Chat_Settings chatSettings = convertor.convertDTOToEntity(dto);
        return repository.save(chatSettings);
    }

    @Override
    public Chat_Settings save(ChatSettingsDTO dto) {
        Chat_Settings settings = convertor.convertDTOToEntity(dto);
        
        if(repository.existsByChatUuid(settings.getChat().getUuid()))
            throw new NotResourceException("exist settings for chat");
        
        return repository.save(settings);
    }

    @Override
    public Optional<Chat_Settings> findByUUID(UUID uuid) {
        return repository.findById(uuid);
    }

    @Override
    public Optional<Chat_Settings> findByChatId(UUID chatId) {
        return repository.findByChatUuid(chatId);
    }

    @Override
    public List<Chat_Settings> findAll(){
        return repository.findAll();
    }
}
