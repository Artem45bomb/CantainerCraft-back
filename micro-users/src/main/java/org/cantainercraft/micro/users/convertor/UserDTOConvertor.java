package org.cantainercraft.micro.users.convertor;

import lombok.RequiredArgsConstructor;
import org.cantainercraft.micro.utilits.service.ConvertorDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.cantainercraft.micro.users.dto.UserDTO;
import org.cantainercraft.project.entity.users.User;

@Component
@RequiredArgsConstructor
public class UserDTOConvertor implements ConvertorDTO<UserDTO,User> {

    private final ModelMapper modelMapper;

    @Override
    public UserDTO convertEntityToDTO(User user) {
        return modelMapper.map(user,UserDTO.class);
    }

    @Override
    public User convertDTOToEntity(UserDTO userDTO) {
        return modelMapper.map(userDTO,User.class);
    }
}
