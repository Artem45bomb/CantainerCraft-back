package org.cantainercraft.micro.chats.service.impl;

import lombok.RequiredArgsConstructor;
import org.cantainercraft.micro.chats.convertor.PrivilegeDTOConvertor;
import org.cantainercraft.micro.chats.dto.PrivilegeDTO;
import org.cantainercraft.micro.chats.service.PrivilegeService;
import org.cantainercraft.micro.utilits.exception.NotResourceException;
import org.cantainercraft.project.entity.chats.Privilege;
import org.cantainercraft.micro.chats.repository.PrivilegeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class PrivilegeServiceImpl implements PrivilegeService {

    private final PrivilegeRepository repository;
    private final PrivilegeDTOConvertor convertor;

    @Override
    public Privilege save(PrivilegeDTO dto) {
        Privilege entity = convertor.convertDTOToEntity(dto);
        if(repository.existsById(dto.getUuid())){
            throw new NotResourceException("This privilege already exists");
        }
        return repository.save(entity);
    }

    @Override
    public Privilege update(PrivilegeDTO dto) {
        Privilege entity = convertor.convertDTOToEntity(dto);
        if (repository.findAll().isEmpty()) {
            throw new NotResourceException("Privilage already exists");
        }
        return repository.save(entity);
    }

    @Override
    public void deleteById(UUID uuid) {
        Optional<Privilege> optional = repository.findById(uuid);
        if (optional.isPresent()) {
            throw new NotResourceException("This privilege does not exist");
        }
        repository.deleteById(uuid);
    }

    @Override
    public List<Privilege> findAll() {
        return repository.findAll();
    }

    @Override
    public Privilege findById(UUID uuid) {
        Optional <Privilege> optional = repository.findById(uuid);
        if (optional.isEmpty()) {
            throw new NotResourceException("This privilege does not exist");
        }
        return optional.get();
    }

    @Override
    public List<Privilege> findByChat(UUID uuid, String name) {
        Optional <Privilege> optional = repository.findById(uuid);

        if (optional.isEmpty()) { // не закончено
            throw new NotResourceException("This privilege does not exist");
        }
        return repository.findPrivilegeByChatUuidOrChatName(uuid, name);
    }

    @Override
    public boolean findByChatIdAndNameRole(UUID chatId, String role) {
        // не закончено
        return repository.findByChatUuidAndNameRole(chatId, role);
    }
}