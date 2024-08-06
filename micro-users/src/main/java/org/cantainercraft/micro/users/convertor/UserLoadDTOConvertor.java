package org.cantainercraft.micro.users.convertor;

import lombok.RequiredArgsConstructor;
import org.cantainercraft.micro.users.dto.UserLoadDTO;
import org.cantainercraft.micro.utilits.service.ConvertorDTO;
import org.cantainercraft.project.entity.users.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserLoadDTOConvertor implements ConvertorDTO<UserLoadDTO,User> {

    private final ModelMapper modelMapper;

    @Override
    public UserLoadDTO convertEntityToDTO(User user) {
        return modelMapper.map(user,UserLoadDTO.class);
    }

    @Override
    public User convertDTOToEntity(UserLoadDTO userDTO) {
        return modelMapper.map(userDTO,User.class);
    }
}
