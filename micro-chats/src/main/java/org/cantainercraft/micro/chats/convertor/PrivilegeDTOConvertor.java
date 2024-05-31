package org.cantainercraft.micro.chats.convertor;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.cantainercraft.micro.chats.dto.PrivilegeDTO;
import org.cantainercraft.project.entity.chats.Privilege;

@Component
@RequiredArgsConstructor
public class PrivilegeDTOConvertor {
    private final ModelMapper modelMapper;

    public PrivilegeDTO convertPrivilegeToPrivilegeDTO(Privilege privilege) {
        return modelMapper.map(privilege, PrivilegeDTO.class);
    }

    public Privilege convertPrivilegeDTOToPrivilege(PrivilegeDTO privilegeDTO) {
        return modelMapper.map(privilegeDTO, Privilege.class);
    }
}