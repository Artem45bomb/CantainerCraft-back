package org.cantainercraft.micro.chats.convertor;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.cantainercraft.micro.chats.dto.UserPrivilegeDTO;
import org.cantainercraft.project.entity.chats.User_Privilege;

@Component
@RequiredArgsConstructor
public class UserPrivilegeDTOConvertor {
    private final ModelMapper modelMapper;

    public UserPrivilegeDTO convertUserPrivilegeToUserPrivilegeDTO(User_Privilege userPrivilege) {
        return modelMapper.map(userPrivilege, UserPrivilegeDTO.class);
    }

    public User_Privilege convertUserPrivilegeDTOToUserPrivilege(UserPrivilegeDTO dto) {
        return modelMapper.map(dto, User_Privilege.class);
    }
}
