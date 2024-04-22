package org.cantainercraft.micro.chats.convertor;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.cantainercraft.micro.chats.dto.ChatDTO;
import org.cantainercraft.project.entity.chats.Chat;

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
