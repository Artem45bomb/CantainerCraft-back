package org.cantainercraft.micro.chats.convertor;

import org.cantainercraft.micro.chats.dto.ChatSettingsChanelDTO;
import org.cantainercraft.micro.chats.dto.ChatSettingsDTO;
import org.cantainercraft.project.entity.chats.Chat_Settings_Chanel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ChatSettingsChanelDTOConvertor {
    @Autowired
    private ModelMapper modelMapper;

    public ChatSettingsChanelDTO convertorChatSettingsChanelToChatSettingsChanelDTO(Chat_Settings_Chanel chanel) {
        return modelMapper.map(chanel, ChatSettingsChanelDTO.class);
    }

    public Chat_Settings_Chanel convertChatSettingsChanelDTOToChatSettingsChanel(ChatSettingsChanelDTO chanelDTO) {
        return modelMapper.map(chanelDTO, Chat_Settings_Chanel.class);
    }
}
