package org.cantainercraft.micro.chats.service.impl;

import lombok.RequiredArgsConstructor;
import org.cantainercraft.micro.chats.convertor.UserPrivilegeDTOConvertor;
import org.cantainercraft.micro.chats.dto.UserPrivilegeDTO;
import org.cantainercraft.micro.chats.service.UserPrivilegeService;
import org.cantainercraft.micro.chats.webflux.UserWebClient;
import org.cantainercraft.micro.utilits.exception.NotResourceException;
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
    private final UserWebClient userClient;

    @Override
    public User_Privilege save(UserPrivilegeDTO dto) {
        User_Privilege entity = convertor.convertDTOToEntity(dto);
        if(!userClient.userExist(dto.getUserId()))
            throw new NotResourceException("user not found");

        if(repository.existsByUserIdAndPrivilege(dto.getUserId(),dto.getPrivilege()))
            throw new NotResourceException("This add privilege for the user");

        return repository.save(entity);
    }

    @Override
    public void delete(UUID uuid) {
        if (!repository.existsById(uuid))
            throw new NotResourceException("content is not exist");

        repository.deleteById(uuid);
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
}
