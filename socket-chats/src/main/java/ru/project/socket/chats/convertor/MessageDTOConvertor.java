package ru.project.socket.chats.convertor;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.project.socket.chats.dto.MessageDTO;
import ru.weather.project.entity.Message;


@Component
public class MessageDTOConvertor {
    @Autowired
    private ModelMapper modelMapper;

    public MessageDTO convertMessageToMessageDTO(Message message){
        return modelMapper.map(message,MessageDTO.class);
    }

    public Message convertMessageDTOToMessage(MessageDTO messageDTO){
        return modelMapper.map(messageDTO,Message.class);
    }
}
