package ru.project.socket.chats.convertor;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.project.socket.chats.dto.ChatDTO;
import ru.weather.project.entity.Chat;


@Component
public class ChatDTOConvertor {
    @Autowired
    private ModelMapper modelMapper;

    public ChatDTO convertChatToChatDTO(Chat chat){
        return modelMapper.map(chat,ChatDTO.class);
    }

    public Chat convertChatDTOToChat(ChatDTO chatDTO){
        return modelMapper.map(chatDTO,Chat.class);
    }
}
