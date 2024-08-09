package org.cantainercraft.micro.chats.convertor;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.cantainercraft.micro.chats.repository.dto.UserChatDTO;
import org.cantainercraft.project.entity.chats.User_Chat;

@Component
public class UserChatDTOConvertor {
    @Autowired
    private ModelMapper modelMapper;

    public UserChatDTO convertEntityToDTO(User_Chat userChat){
        return modelMapper.map(userChat, UserChatDTO.class);
    }

    public User_Chat convertDTOToEntity(UserChatDTO dto){
        return modelMapper.map(dto, User_Chat.class);
    }
}
