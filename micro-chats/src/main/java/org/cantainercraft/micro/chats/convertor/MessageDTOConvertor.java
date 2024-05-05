package org.cantainercraft.micro.chats.convertor;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.cantainercraft.micro.chats.dto.MessageDTO;
import org.cantainercraft.project.entity.chats.Message;


@Component
@RequiredArgsConstructor
public class MessageDTOConvertor {
    @Autowired
    private final ModelMapper modelMapper;

    public MessageDTO convertMessageToMessageDTO(Message message){
        return modelMapper.map(message,MessageDTO.class);
    }

    public Message convertMessageDTOToMessage(MessageDTO messageDTO){
        return modelMapper.map(messageDTO,Message.class);
    }
}
