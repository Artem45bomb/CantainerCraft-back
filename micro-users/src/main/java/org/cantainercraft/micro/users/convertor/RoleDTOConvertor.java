package org.cantainercraft.micro.users.convertor;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.cantainercraft.micro.users.dto.RoleDTO;
import org.cantainercraft.project.entity.users.Role;

@Component
@RequiredArgsConstructor
public class RoleDTOConvertor {

    private final ModelMapper modelMapper;

    public RoleDTO convertRoleToRoleDTO(Role role){
        return modelMapper.map(role,RoleDTO.class);
    }

    public Role convertRoleDTOToRole(RoleDTO roleDTO){
        return modelMapper.map(roleDTO,Role.class);
    }
}
