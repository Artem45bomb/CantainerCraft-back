package org.cantainercraft.micro.users.convertor;

import lombok.RequiredArgsConstructor;
import org.cantainercraft.micro.utilits.service.ConvertorDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.cantainercraft.micro.users.dto.UserOnlineDTO;
import org.cantainercraft.project.entity.users.User_Online;

@Component
@RequiredArgsConstructor
public class UserOnlineDTOConvertor implements ConvertorDTO<UserOnlineDTO, User_Online> {

    private final ModelMapper modelMapper;


    @Override
    public UserOnlineDTO convertEntityToDTO(User_Online userOnline) {
        return modelMapper.map(userOnline, UserOnlineDTO.class);
    }

    @Override
    public User_Online convertDTOToEntity(UserOnlineDTO userOnlineDTO) {
       return modelMapper.map(userOnlineDTO, User_Online.class);
    }
}
