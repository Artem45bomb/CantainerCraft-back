package org.cantainercraft.micro.chats.convertor;

import lombok.RequiredArgsConstructor;
import org.cantainercraft.micro.utilits.service.ConvertorDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.cantainercraft.micro.chats.repository.dto.ChatImageProfileDTO;
import org.cantainercraft.project.entity.chats.Chat_Image_Profile;

@Component
@RequiredArgsConstructor
public class ChatImageProfileDTOConvertor implements ConvertorDTO<ChatImageProfileDTO,Chat_Image_Profile> {
    private final ModelMapper modelMapper;
    
    @Override
    public ChatImageProfileDTO convertEntityToDTO(Chat_Image_Profile object) {
        return modelMapper.map(object, ChatImageProfileDTO.class);
    }

    @Override
    public Chat_Image_Profile convertDTOToEntity(ChatImageProfileDTO dto) {
        return modelMapper.map(dto, Chat_Image_Profile.class);
    }
}
