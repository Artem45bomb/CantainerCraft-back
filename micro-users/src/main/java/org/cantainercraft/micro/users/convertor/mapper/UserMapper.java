package org.cantainercraft.micro.users.convertor.mapper;


import org.cantainercraft.micro.users.dto.UserClientDTO;
import org.cantainercraft.micro.users.dto.UserDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDTO toDTO(UserClientDTO userClient);
}
