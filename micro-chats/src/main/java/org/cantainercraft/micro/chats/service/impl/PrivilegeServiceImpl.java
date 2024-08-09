package org.cantainercraft.micro.chats.service.impl;

import lombok.RequiredArgsConstructor;
import org.cantainercraft.micro.chats.convertor.PrivilegeDTOConvertor;
import org.cantainercraft.micro.chats.dto.PrivilegeDTO;
import org.cantainercraft.micro.chats.service.PrivilegeService;
import org.cantainercraft.micro.utilits.exception.NotResourceException;
import org.cantainercraft.project.entity.chats.Privilege;
import org.cantainercraft.micro.chats.repository.PrivilegeRepository;
import org.cantainercraft.project.entity.users.User_Online;
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

    //исправить
    @Override
    public Privilege save(PrivilegeDTO dto) {
        Privilege entity = convertor.convertDTOToEntity(dto);
        if(repository.existsById(dto.getUuid())){
            throw new NotResourceException("This privilege already exists");
        }
        return repository.save(entity);
    }

    //исправить
    @Override
    public Privilege update(PrivilegeDTO dto) {
        Privilege entity = convertor.convertDTOToEntity(dto);
        Optional<Privilege> privilege = repository.findById(dto.getUuid());
        if (privilege.isEmpty()) {
            throw new NotResourceException("Privilege already exists");
        }
        return repository.save(entity);
    }

    @Override
    public void deleteById(UUID uuid) {
        Optional<Privilege> optional = repository.findById(uuid);
        if (optional.isPresent()) {
            throw new NotResourceException("This privilege does not exist");
        }
        repository.deleteById(uuid);}


    @Override
    public List<Privilege> findAll() {
        return repository.findAll();
    }

    //исправить
    @Override
    public Optional<Privilege> findById(UUID uuid) {
        Optional <Privilege> optional = repository.findById(uuid);
        if (optional.isEmpty()) {
            throw new NotResourceException("This privilege does not exist");
        }
        return repository.findById(uuid);
    }

    //исправить
    @Override
    public Optional<List<Privilege>> findByChat(UUID chatId) {
        Optional <Privilege> optional = repository.findById(chatId);
        if(optional.isEmpty())
        {
            throw new NotResourceException("This chat does not exist");
        }
        return repository.findByChat(chatId);
    }

    @Override
    public boolean findByChatIdAndNameRole(UUID chatId, String role) {
        // не закончено
        Optional <Privilege> optional = repository.findById(chatId);
        if(optional.isEmpty()){
            throw new NotResourceException("This privilege does not exist");
        }
        return repository.findByChatUuidAndNameRole(chatId, role);
    }
}