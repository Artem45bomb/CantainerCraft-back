package ru.javabegin.backend.todo.eurekaclient.convertor;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.javabegin.backend.todo.eurekaclient.dto.RoleDTO;
import ru.weather.project.entity.Role;

@Component
public class RoleDTOConvertor {
    @Autowired
    private ModelMapper modelMapper;

    public RoleDTO convertRoleToRoleDTO(Role role){
        return modelMapper.map(role,RoleDTO.class);
    }

    public Role convertRoleDTOToRole(RoleDTO roleDTO){
        return modelMapper.map(roleDTO,Role.class);
    }
}
