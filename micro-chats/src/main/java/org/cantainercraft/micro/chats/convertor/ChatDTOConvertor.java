package org.cantainercraft.micro.chats.convertor;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.cantainercraft.micro.chats.dto.ChatDTO;
import org.cantainercraft.project.entity.chats.Chat;

@Component
@RequiredArgsConstructor
public class ChatDTOConvertor {
    private final ModelMapper modelMapper;

    public ChatDTO convertChatToChatDTO(Chat chat){
        return modelMapper.map(chat,ChatDTO.class);
    }

    public Chat convertChatDTOToChat(ChatDTO chatDTO){
        return modelMapper.map(chatDTO,Chat.class);
    }
}
