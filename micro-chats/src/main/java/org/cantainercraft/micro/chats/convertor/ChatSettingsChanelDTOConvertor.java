package org.cantainercraft.micro.chats.convertor;

import org.cantainercraft.micro.chats.dto.ChatSettingsChanelDTO;
import org.cantainercraft.micro.utilits.service.ConvertorDTO;
import org.cantainercraft.project.entity.chats.Chat_Settings_Chanel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ChatSettingsChanelDTOConvertor implements ConvertorDTO<ChatSettingsChanelDTO,Chat_Settings_Chanel> {
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ChatSettingsChanelDTO convertEntityToDTO(Chat_Settings_Chanel object) {
        return modelMapper.map(object, ChatSettingsChanelDTO.class);
    }

    @Override
    public Chat_Settings_Chanel convertDTOToEntity(ChatSettingsChanelDTO dto) {
        return modelMapper.map(dto, Chat_Settings_Chanel.class);
    }
}
