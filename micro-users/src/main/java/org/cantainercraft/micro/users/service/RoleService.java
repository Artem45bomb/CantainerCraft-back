package org.cantainercraft.micro.users.service;

import org.cantainercraft.micro.users.convertor.RoleDTOConvertor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.cantainercraft.micro.users.dto.RoleDTO;
import org.cantainercraft.micro.users.dto.RoleUpdateDTO;
import org.cantainercraft.project.entity.Role;
import org.cantainercraft.micro.users.repository.RoleRepository;

import java.util.List;

@Component
@Transactional
public class RoleService {
    
    private final org.cantainercraft.micro.users.convertor.RoleDTOConvertor RoleDTOConvertor;
    private final RoleRepository RoleRepository;
    
    public RoleService(RoleRepository RoleRepository, RoleDTOConvertor RoleDTOConvertor) {
        this.RoleRepository = RoleRepository;
        this.RoleDTOConvertor =RoleDTOConvertor;
    }

    public List<Role> findAll(){
        return RoleRepository.findAll();
    }

    public Role findById(Long id){
        return RoleRepository.findById(id).get();
    }
    
    public Role save(RoleDTO RoleDTO){
        Role Role = RoleDTOConvertor.convertRoleDTOToRole(RoleDTO);
        return RoleRepository.save(Role);
    }
    
    public boolean update(RoleUpdateDTO RoleUpdateDTO){
        Role Role = RoleDTOConvertor.convertRoleDTOToRole(RoleUpdateDTO);
        Role.setId(RoleUpdateDTO.getId());
        RoleRepository.save(Role);
        return  true;
    }

    public void delete(Long id){
        RoleRepository.deleteById(id);
    }
}
