package org.cantainercraft.micro.chats.convertor;

import lombok.RequiredArgsConstructor;
import org.cantainercraft.micro.utilits.service.ConvertorDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.cantainercraft.micro.chats.dto.ChatDTO;
import org.cantainercraft.project.entity.chats.Chat;

@Component
@RequiredArgsConstructor
public class ChatDTOConvertor implements ConvertorDTO<ChatDTO,Chat> {
    private final ModelMapper modelMapper;

    @Override
    public ChatDTO convertEntityToDTO(Chat object) {
        return modelMapper.map(object,ChatDTO.class);
    }

    @Override
    public Chat convertDTOToEntity(ChatDTO chatDTO) {
        return modelMapper.map(chatDTO,Chat.class);
    }
}
