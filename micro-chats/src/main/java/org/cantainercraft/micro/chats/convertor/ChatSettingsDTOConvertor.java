package org.cantainercraft.micro.chats.convertor;

import org.cantainercraft.micro.chats.dto.ChatSettingsDTO;
import org.cantainercraft.project.entity.chats.Chat_Settings;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ChatSettingsDTOConvertor {
    @Autowired
    private ModelMapper modelMapper;

    public ChatSettingsDTO convertChatSettingsToChatSettingsDTO(Chat_Settings chat_settings) {
        return modelMapper.map(chat_settings, ChatSettingsDTO.class);
    }

    public Chat_Settings convertChatSettingsDTOToChatSettings(ChatSettingsDTO chat_settings_dto) {
        return modelMapper.map(chat_settings_dto, Chat_Settings.class);
    }
}
