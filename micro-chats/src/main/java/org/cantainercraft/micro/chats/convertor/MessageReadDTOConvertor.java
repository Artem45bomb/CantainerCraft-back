package org.cantainercraft.micro.chats.convertor;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.cantainercraft.micro.chats.repository.dto.MessageReadDTO;
import org.cantainercraft.project.entity.chats.MessageRead;

@Component
@RequiredArgsConstructor
public class MessageReadDTOConvertor {
    private final ModelMapper modelMapper;

    public MessageReadDTO convertEntityToDTO(MessageRead messageRead) {
        return modelMapper.map(messageRead, MessageReadDTO.class);
    }

    public MessageRead convertDTOToEntity(MessageReadDTO messageReadDTO) {
        return modelMapper.map(messageReadDTO, MessageRead.class);
    }
}
