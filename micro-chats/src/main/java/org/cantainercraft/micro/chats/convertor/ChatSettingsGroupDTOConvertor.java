package org.cantainercraft.micro.chats.convertor;

import org.cantainercraft.micro.chats.dto.ChatSettingsGroupDTO;
import org.cantainercraft.project.entity.chats.Chat_Settings_Group;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ChatSettingsGroupDTOConvertor {
    @Autowired
    private ModelMapper modelMapper;

    public ChatSettingsGroupDTO convertEntityToDTO(Chat_Settings_Group chatSettingsGroup) {
        return modelMapper.map(chatSettingsGroup, ChatSettingsGroupDTO.class);
    }

    public Chat_Settings_Group convertDTOToEntity(ChatSettingsGroupDTO chatSettingsGroupDTO) {
        return modelMapper.map(chatSettingsGroupDTO, Chat_Settings_Group.class);
    }

}
