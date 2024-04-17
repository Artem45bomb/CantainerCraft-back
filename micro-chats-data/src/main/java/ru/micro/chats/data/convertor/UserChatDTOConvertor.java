package ru.micro.chats.data.convertor;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.micro.chats.data.dto.UserChatDTO;
import ru.weather.project.entity.chats.User_Chat;

@Component
public class UserChatDTOConvertor {
    @Autowired
    private ModelMapper modelMapper;

    public UserChatDTO convertUserChatToUserChatDTO(User_Chat userChat){
        return modelMapper.map(userChat,UserChatDTO.class);
    }

    public User_Chat convertUserChatDTOToUserChat(UserChatDTO userChatDTO){
        return modelMapper.map(userChatDTO,User_Chat.class);
    }
}
