package org.cantainercraft.micro.users.service.impl;

import lombok.RequiredArgsConstructor;
import org.cantainercraft.micro.users.dto.RoleDTO;
import org.cantainercraft.micro.users.service.RoleService;
import org.cantainercraft.micro.utilits.exception.ExistResourceException;
import org.cantainercraft.micro.utilits.exception.NotResourceException;
import org.cantainercraft.micro.utilits.service.ConvertorDTO;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.cantainercraft.project.entity.users.Role;
import org.cantainercraft.micro.users.repository.RoleRepository;

import java.util.List;
import java.util.Optional;

@Component
@Transactional
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final ConvertorDTO<RoleDTO,Role> roleDTOConvertor;
    private final RoleRepository roleRepository;


    @Override
    public List<Role> findAll(){
        return roleRepository.findAll();
    }

    @Override
    @Cacheable(value = "roles",key = "#id")
    public Role findById(Long id){
        Optional<Role> role = roleRepository.findById(id);

        if(role.isEmpty()) throw new NotResourceException("role is not exist");

        return role.get();
    }

    @Override
    @Cacheable(value = "roles",key = "#result.id")
    public Role save(RoleDTO dto){
        Optional<Role> entity = roleRepository.findByRole(dto.getRole());

        if(entity.isPresent()) throw new ExistResourceException("role is exist");
        Role role = roleDTOConvertor.convertDTOToEntity(dto);

        return roleRepository.save(role);
    }

    @Override
    @CachePut(value = "roles",key = "#dto.id")
    public Role update(RoleDTO dto){
        Optional<Role> role = roleRepository.findById(dto.getId());
        Optional<Role> roleExist = roleRepository.findByRole(dto.getRole());

        if(role.isEmpty()) throw new NotResourceException("role is not exist");
        if(roleExist.isPresent()) throw new ExistResourceException("a role with the same name exists");

        return roleRepository.save(roleDTOConvertor.convertDTOToEntity(dto));
    }

    @Override
    @CacheEvict(value = "roles",key = "#id")
    public void deleteById(Long id){
        Optional<Role> role = roleRepository.findById(id);

        if(role.isEmpty()) throw new NotResourceException("role is not exist");

        roleRepository.deleteById(id);
    }
}
