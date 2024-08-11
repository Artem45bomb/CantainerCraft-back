package org.cantainercraft.micro.chats.convertor;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.cantainercraft.micro.chats.repository.dto.MessageForwardDTO;
import org.cantainercraft.project.entity.chats.Message_Forward;

@Component
@RequiredArgsConstructor
public class MessageForwardDTOConvertor {
    private final ModelMapper modelMapper;

    public MessageForwardDTO convertEntityToDTO(Message_Forward messageForward) {
        return modelMapper.map(messageForward, MessageForwardDTO.class);
    }

    public Message_Forward convertDTOToEntity(MessageForwardDTO messageForwardDTO) {
        return modelMapper.map(messageForwardDTO, Message_Forward.class);
    }
}
