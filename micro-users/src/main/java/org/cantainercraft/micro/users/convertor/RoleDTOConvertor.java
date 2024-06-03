package org.cantainercraft.micro.users.convertor;

import lombok.RequiredArgsConstructor;
import org.cantainercraft.micro.utilits.service.ConvertorDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.cantainercraft.micro.users.dto.RoleDTO;
import org.cantainercraft.project.entity.users.Role;

@Component
@RequiredArgsConstructor
public class RoleDTOConvertor implements ConvertorDTO<RoleDTO,Role> {

    private final ModelMapper modelMapper;

    @Override
    public RoleDTO convertEntityToDTO(Role role) {
        return modelMapper.map(role,RoleDTO.class);
    }

    @Override
    public Role convertDTOToEntity(RoleDTO roleDTO) {
        return modelMapper.map(roleDTO,Role.class);
    }
}
