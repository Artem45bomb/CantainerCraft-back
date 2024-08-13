package org.cantainercraft.micro.chats.service.impl;

import lombok.RequiredArgsConstructor;
import org.cantainercraft.micro.chats.convertor.ChatSettingsDTOConvertor;
import org.cantainercraft.micro.chats.repository.dto.ChatSettingsDTO;
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

    public void delete(UUID uuid) {
        if(!repository.existsById(uuid))
        {
            throw new NotResourceException("no settings by id");
        }
        repository.deleteById(uuid);
    }


    public Chat_Settings update(ChatSettingsDTO chatSettingsUpdateDTO) {
        if(!repository.existsById(chatSettingsUpdateDTO.getUuid())){
            throw new NotResourceException("no settings by id");
        }
        Chat_Settings chatSettings = convertor.convertDTOToEntity(chatSettingsUpdateDTO);
        return repository.save(chatSettings);
    }

    public Chat_Settings save(ChatSettingsDTO settings) {
        Chat_Settings chatSettings = convertor.convertDTOToEntity(settings);
        return repository.save(chatSettings);
    }

    public Optional<Chat_Settings> findByUUID(UUID uuid) {
        return repository.findById(uuid);
    }

    public List<Chat_Settings> findByChatId(UUID chat_Id) {
        return repository.findByChatUuid(chat_Id);
    }
    
    public List<Chat_Settings> findAll(){
        return repository.findAll();
    }
}
