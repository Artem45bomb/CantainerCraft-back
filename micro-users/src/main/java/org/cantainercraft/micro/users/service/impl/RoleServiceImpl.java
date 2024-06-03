package org.cantainercraft.micro.users.service.impl;

import lombok.RequiredArgsConstructor;
import org.cantainercraft.micro.users.convertor.RoleDTOConvertor;
import org.cantainercraft.micro.users.service.RoleService;
import org.cantainercraft.micro.utilits.exception.NotResourceException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.cantainercraft.micro.users.dto.RoleDTO;
import org.cantainercraft.micro.users.dto.RoleUpdateDTO;
import org.cantainercraft.project.entity.users.Role;
import org.cantainercraft.micro.users.repository.RoleRepository;

import java.util.List;
import java.util.Optional;

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
        Optional<Role> role = roleRepository.findById(id);

        if(role.isEmpty()){
            throw new NotResourceException("role is not exist");
        }

        return role.get();
    }
    
    public Role save(RoleDTO RoleDTO){
        Role Role = roleDTOConvertor.convertDTOToEntity(RoleDTO);
        return roleRepository.save(Role);
    }
    
    public boolean update(RoleUpdateDTO RoleUpdateDTO){
        Role Role = roleDTOConvertor.convertDTOToEntity(RoleUpdateDTO);
        Role.setId(RoleUpdateDTO.getId());
        roleRepository.save(Role);
        return  true;
    }

    public void deleteById(Long id){
        roleRepository.deleteById(id);
    }
}
