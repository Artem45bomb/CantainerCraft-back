package org.cantainercraft.micro.users.convertor;

import lombok.RequiredArgsConstructor;
import org.cantainercraft.micro.users.dto.UserClientDTO;
import org.cantainercraft.micro.utilits.service.ConvertorDTO;
import org.cantainercraft.project.entity.users.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserClientDTOConvertor implements ConvertorDTO<UserClientDTO,User> {

    private final ModelMapper modelMapper;

    @Override
    public UserClientDTO convertEntityToDTO(User user) {
        return modelMapper.map(user,UserClientDTO.class);
    }

    @Override
    public User convertDTOToEntity(UserClientDTO userDTO) {
        return modelMapper.map(userDTO,User.class);
    }
}
