package org.cantainercraft.micro.users.convertor;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.cantainercraft.micro.users.dto.UserDTO;
import org.cantainercraft.project.entity.User;

@Component
@RequiredArgsConstructor
public class UserDTOConvertor {

    private final ModelMapper modelMapper;

    public UserDTO convertUserToUserDTO(User user){
        return modelMapper.map(user,UserDTO.class);
    }

    public User convertUserDTOToUser(UserDTO userDTO){
        return modelMapper.map(userDTO,User.class);
    }
}
