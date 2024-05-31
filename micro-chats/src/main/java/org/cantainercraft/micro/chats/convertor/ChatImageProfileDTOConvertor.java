package org.cantainercraft.micro.chats.convertor;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.cantainercraft.micro.chats.dto.ChatImageProfileDTO;
import org.cantainercraft.project.entity.chats.Chat_Image_Profile;

@Component
@RequiredArgsConstructor
public class ChatImageProfileDTOConvertor {
    private final ModelMapper modelMapper;

    public ChatImageProfileDTO convertChatImageProfileToChatImageProfileDTO(Chat_Image_Profile chatImageProfile) {
        return modelMapper.map(chatImageProfile, ChatImageProfileDTO.class);
    }

    public Chat_Image_Profile convertChatImageProfileDTOToChatImageProfile(ChatImageProfileDTO chatImageProfileDTO) {
        return modelMapper.map(chatImageProfileDTO, Chat_Image_Profile.class);
    }
}
