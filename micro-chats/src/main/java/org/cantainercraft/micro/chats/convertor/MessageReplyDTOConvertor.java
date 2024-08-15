package org.cantainercraft.micro.chats.convertor;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.cantainercraft.micro.chats.dto.MessageReplyDTO;
import org.cantainercraft.project.entity.chats.Message_Reply;

@Component
@RequiredArgsConstructor
public class MessageReplyDTOConvertor {
    private final ModelMapper modelMapper;

    public MessageReplyDTO convertEntityToDTO(Message_Reply messageReply) {
        return modelMapper.map(messageReply, MessageReplyDTO.class);
    }

    public Message_Reply convertDTOToEntity(MessageReplyDTO messageReplyDTO) {
        return modelMapper.map(messageReplyDTO, Message_Reply.class);
    }
}
