package org.cantainercraft.micro.chats.service.impl;

import lombok.RequiredArgsConstructor;
import org.cantainercraft.micro.chats.convertor.ChatSettingsChanelDTOConvertor;
import org.cantainercraft.micro.chats.dto.ChatSettingsChanelDTO;
import org.cantainercraft.micro.chats.repository.ChatSettingsChanelRepository;
import org.cantainercraft.micro.chats.service.ChatSettingsChanelService;
import org.cantainercraft.micro.utilits.exception.NotResourceException;
import org.cantainercraft.project.entity.chats.Chat_Settings_Chanel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class ChatSettingsChanelServiceImpl implements ChatSettingsChanelService {

    private final ChatSettingsChanelRepository chatSettingsChanelRepository;
    private final ChatSettingsChanelDTOConvertor chatSettingsChanelDTOConvertor;

    public boolean delete(UUID uuid) {
        if(!chatSettingsChanelRepository.existsById(uuid)) {
            throw new NotResourceException("no settingsChanel by id");
        }
        chatSettingsChanelRepository.deleteById(uuid);
        return true;
    }

    public Chat_Settings_Chanel save(ChatSettingsChanelDTO chatSettingsChanelDTO) {
        Chat_Settings_Chanel chatSettingsChanel = chatSettingsChanelDTOConvertor.convertChatSettingsChanelDTOToChatSettingsChanel(chatSettingsChanelDTO);
        return chatSettingsChanelRepository.save(chatSettingsChanel);
    }

    public boolean update(ChatSettingsChanelDTO chatSettingsChanelUpdateDTO) {
        if(!chatSettingsChanelRepository.existsById(chatSettingsChanelUpdateDTO.getUuid())) {
            throw new NotResourceException("no settingsChanel by id");
        }
        Chat_Settings_Chanel chatSettingsChanel = chatSettingsChanelDTOConvertor.convertChatSettingsChanelDTOToChatSettingsChanel(chatSettingsChanelUpdateDTO);
        chatSettingsChanelRepository.save(chatSettingsChanel);
        return true;
    }

    public Optional<Chat_Settings_Chanel> findByUUID(UUID uuid) {
        return chatSettingsChanelRepository.findById(uuid);
    }
}
