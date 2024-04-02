package ru.javabegin.backend.todo.eurekaclient.convertor;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.javabegin.backend.todo.eurekaclient.dto.UserDTO;
import ru.javabegin.backend.todo.eurekaclient.entity.User;

@Component
public class UserDTOConvertor {

    @Autowired
    private ModelMapper modelMapper;

    public UserDTO convertUserToUserDTO(User user){
        return modelMapper.map(user,UserDTO.class);
    }

    public User convertUserDTOToUser(UserDTO userDTO){
        return modelMapper.map(userDTO,User.class);
    }
}
