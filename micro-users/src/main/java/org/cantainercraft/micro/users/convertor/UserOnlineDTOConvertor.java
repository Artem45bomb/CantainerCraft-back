package org.cantainercraft.micro.users.convertor;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.cantainercraft.micro.users.dto.UserOnlineDTO;
import org.cantainercraft.project.entity.users.User_Online;

@Component
@RequiredArgsConstructor
public class UserOnlineDTOConvertor {

    private final ModelMapper modelMapper;

    public UserOnlineDTO convertUserOnlinetoUserOnlineDTO(User_Online userOnline) {
        return modelMapper.map(userOnline, UserOnlineDTO.class);
    }

    public User_Online convertUserOnlineDTOtoUserOnline(UserOnlineDTO userOnlineDTO) {

        return modelMapper.map(userOnlineDTO, User_Online.class);
    }




}
