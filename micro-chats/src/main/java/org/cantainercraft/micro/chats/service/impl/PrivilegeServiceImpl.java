package org.cantainercraft.micro.chats.service.impl;

import lombok.RequiredArgsConstructor;
import org.cantainercraft.micro.chats.convertor.PrivilegeDTOConvertor;
import org.cantainercraft.micro.chats.dto.PrivilegeDTO;
import org.cantainercraft.micro.chats.service.PrivilegeService;
import org.cantainercraft.project.entity.chats.Privilege;
import org.cantainercraft.micro.chats.repository.PrivilegeRepository;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PrivilegeServiceImpl implements PrivilegeService {

    private final PrivilegeRepository repository;
    private final PrivilegeDTOConvertor convertor;

    @Override
    public Privilege save(PrivilegeDTO privilegeDTO) {
        Privilege entity = convertor.convertPrivilegeDTOToPrivilege(privilegeDTO);
        return repository.save(entity);
    }

    @Override
    public boolean update(PrivilegeDTO privilegeDTO) {
        if (repository.existsById(privilegeDTO.getUuid())) {
            Privilege entity = convertor.convertPrivilegeDTOToPrivilege(privilegeDTO);
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
    public List<Privilege> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Privilege> findById(UUID uuid) {
        return repository.findById(uuid);
    }

    @Override
    public List<Privilege> findByChat(UUID uuid, String name) {
        return repository.findPrivilegeByChatUuidOrChatName(uuid, name);
    }
}