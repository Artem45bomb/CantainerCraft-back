package org.cantainercraft.micro.chats.service.impl;

import lombok.RequiredArgsConstructor;
import org.cantainercraft.micro.chats.convertor.ChatSettingsDTOConvertor;
import org.cantainercraft.micro.chats.dto.ChatSettingsDTO;
import org.cantainercraft.micro.chats.repository.ChatSettingsRepository;
import org.cantainercraft.micro.chats.service.ChatSettingsService;
import org.cantainercraft.micro.utilits.exception.NotResourceException;
import org.cantainercraft.project.entity.chats.Chat_Settings;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ChatSettingsServiceImpl implements ChatSettingsService {

    private final ChatSettingsDTOConvertor chatSettingsDTOConvector;
    private final ChatSettingsRepository chatSettingsRepository;

    public boolean delete(UUID uuid) {
        if(!chatSettingsRepository.existsById(uuid))
        {
            throw new NotResourceException("NoN");
        }
        chatSettingsRepository.deleteById(uuid);
        return true;
    }

    public boolean update(ChatSettingsDTO chatSettingsUpdateDTO) {
        if(!chatSettingsRepository.existsById(chatSettingsUpdateDTO.getUuid())){
            throw new NotResourceException("NoN");
        }
        Chat_Settings chatSettings = chatSettingsDTOConvector.convertChatSettingsDTOToChatSettings(chatSettingsUpdateDTO);
        chatSettings.setUuid(chatSettingsUpdateDTO.getUuid());
        chatSettingsRepository.save(chatSettings);
        return true;
    }

    public Chat_Settings save(ChatSettingsDTO settings) {
        Chat_Settings chatSettings = chatSettingsDTOConvector.convertChatSettingsDTOToChatSettings(settings);
        return chatSettingsRepository.save(chatSettings);
    }

    public Optional<Chat_Settings> findByUUID(UUID uuid) {
        return chatSettingsRepository.findById(uuid);
    }

    public List<Chat_Settings> findByChatId(UUID chat_Id) {
        return chatSettingsRepository.findByChatUuid(chat_Id);
    }
}
