package org.cantainercraft.micro.users.convertor;

import org.cantainercraft.micro.users.dto.ChatInfoDTO;
import org.cantainercraft.project.entity.users.Chat_Info;
import org.cantainercraft.micro.utilits.service.ConvertorDTO;
import org.modelmapper.ModelMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ChatInfoDTOConvertor implements ConvertorDTO<ChatInfoDTO, Chat_Info> {

    private final ModelMapper modelMapper;


    @Override
    public ChatInfoDTO convertEntityToDTO(Chat_Info chatInfo) {
        return modelMapper.map(chatInfo, ChatInfoDTO.class);
    }

    @Override
    public Chat_Info convertDTOToEntity(ChatInfoDTO chatInfoDTO) {
        return modelMapper.map(chatInfoDTO, Chat_Info.class);
    }
}
