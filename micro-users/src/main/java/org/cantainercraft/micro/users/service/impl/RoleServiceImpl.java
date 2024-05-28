package org.cantainercraft.micro.users.service.impl;

import lombok.RequiredArgsConstructor;
import org.cantainercraft.micro.users.convertor.RoleDTOConvertor;
import org.cantainercraft.micro.users.service.RoleService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.cantainercraft.micro.users.dto.RoleDTO;
import org.cantainercraft.micro.users.dto.RoleUpdateDTO;
import org.cantainercraft.project.entity.users.Role;
import org.cantainercraft.micro.users.repository.RoleRepository;

import java.util.List;

@Component
@Transactional
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    
    private final RoleDTOConvertor roleDTOConvertor;
    private final RoleRepository roleRepository;


    public List<Role> findAll(){
        return roleRepository.findAll();
    }

    public Role findById(Long id){
        return roleRepository.findById(id).get();
    }
    
    public Role save(RoleDTO RoleDTO){
        Role Role = roleDTOConvertor.convertRoleDTOToRole(RoleDTO);
        return roleRepository.save(Role);
    }
    
    public boolean update(RoleUpdateDTO RoleUpdateDTO){
        Role Role = roleDTOConvertor.convertRoleDTOToRole(RoleUpdateDTO);
        Role.setId(RoleUpdateDTO.getId());
        roleRepository.save(Role);
        return  true;
    }

    public void deleteById(Long id){
        roleRepository.deleteById(id);
    }
}
