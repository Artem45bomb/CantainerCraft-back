package org.cantainercraft.micro.chats.convertor;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.cantainercraft.micro.chats.repository.dto.MessageDTO;
import org.cantainercraft.project.entity.chats.Message;


@Component
@RequiredArgsConstructor
public class MessageDTOConvertor {
    private final ModelMapper modelMapper;

    public MessageDTO convertEntityToDTO(Message message){
        return modelMapper.map(message,MessageDTO.class);
    }

    public Message convertDTOToEntity(MessageDTO messageDTO){
        return modelMapper.map(messageDTO,Message.class);
    }
}
