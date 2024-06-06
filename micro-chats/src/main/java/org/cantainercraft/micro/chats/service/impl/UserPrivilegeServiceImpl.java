package org.cantainercraft.micro.chats.service.impl;

import lombok.RequiredArgsConstructor;
import org.cantainercraft.micro.chats.convertor.UserPrivilegeDTOConvertor;
import org.cantainercraft.micro.chats.dto.UserPrivilegeDTO;
import org.cantainercraft.micro.chats.service.UserPrivilegeService;
import org.cantainercraft.project.entity.chats.User_Privilege;
import org.cantainercraft.micro.chats.repository.UserPrivilegeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserPrivilegeServiceImpl implements UserPrivilegeService {

    private final UserPrivilegeRepository repository;
    private final UserPrivilegeDTOConvertor convertor;

    @Override
    public User_Privilege save(UserPrivilegeDTO userPrivilegeDTO) {
        User_Privilege entity = convertor.convertUserPrivilegeDTOToUserPrivilege(userPrivilegeDTO);
        return repository.save(entity);
    }

    @Override
    public boolean update(UserPrivilegeDTO userPrivilegeDTO) {
        if (repository.existsById(userPrivilegeDTO.getUuid())) {
            User_Privilege entity = convertor.convertUserPrivilegeDTOToUserPrivilege(userPrivilegeDTO);
            repository.save(entity);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(UUID uuid) {
        if (repository.existsById(uuid)) {
            repository.deleteById(uuid);
            return true;
        }
        return false;
    }

    @Override
    public List<User_Privilege> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<User_Privilege> findById(UUID uuid) {
        return repository.findById(uuid);
    }

    @Override
    public Optional<User_Privilege> findByUserId(Long id) {
        return repository.findByUserId(id);
    }

    @Override
    public boolean deleteByUserId(Long id) {
        if (repository.findByUserId(id).isPresent()) {
            repository.deleteByUserId(id);
            return true;
        }
        return false;
    }
}
