package org.containercraft.servicerealtime.convertor;

import lombok.RequiredArgsConstructor;
import org.cantainercraft.micro.utilits.service.ConvertorDTO;
import org.containercraft.servicerealtime.dto.UserDTO;
import org.containercraft.servicerealtime.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserDTOConvertor implements ConvertorDTO<UserDTO, User> {
    private final ModelMapper mapper;
            
    @Override
    public UserDTO convertEntityToDTO(User room) {
        return mapper.map(room,UserDTO.class);
    }

    @Override
    public User convertDTOToEntity(UserDTO dto) {
        return mapper.map(dto,User.class);
    }
}
