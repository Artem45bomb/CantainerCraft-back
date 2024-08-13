package org.cantainercraft.micro.chats.convertor;

import org.cantainercraft.micro.chats.repository.dto.ChatSettingsDTO;
import org.cantainercraft.project.entity.chats.Chat_Settings;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ChatSettingsDTOConvertor {
    @Autowired
    private ModelMapper modelMapper;

    public ChatSettingsDTO convertEntityToDTO(Chat_Settings chat_settings) {
        return modelMapper.map(chat_settings, ChatSettingsDTO.class);
    }

    public Chat_Settings convertDTOToEntity(ChatSettingsDTO chat_settings_dto) {
        return modelMapper.map(chat_settings_dto, Chat_Settings.class);
    }
}
