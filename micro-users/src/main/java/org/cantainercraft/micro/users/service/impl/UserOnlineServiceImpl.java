package org.cantainercraft.micro.users.service.impl;

import lombok.RequiredArgsConstructor;
import org.cantainercraft.micro.users.convertor.UserOnlineDTOConvertor;
import org.cantainercraft.micro.users.dto.UserOnlineDTO;
import org.cantainercraft.micro.users.repository.UserOnlineRepository;
import org.cantainercraft.micro.users.service.UserOnlineService;
import org.cantainercraft.micro.utilits.exception.ExistResourceException;
import org.cantainercraft.micro.utilits.exception.NotResourceException;
import org.cantainercraft.project.entity.users.User_Online;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class UserOnlineServiceImpl implements UserOnlineService {
    private final UserOnlineRepository repository;
    private final UserOnlineDTOConvertor dtoConvertor;

    @Override
    public List<User_Online> findAll() {
        return repository.findAll();
    }

    @Override
    @Cacheable(value = "user-online",key = "#uuid",unless = "#result == null ")
    public Optional<User_Online> findById(UUID uuid) {
       return repository.findById(uuid);
    }

    @Override
    public User_Online save(UserOnlineDTO dto) {
        User_Online userOnline = dtoConvertor.convertDTOToEntity(dto);
        if (repository.existsByUserId(dto.getUser().getId())) {
            throw new ExistResourceException("UserOnline already exists");
        }

        return repository.save(userOnline);
    }

    @Override
    @CachePut(value = "user-online",key = "#dto.uuid")
    public User_Online update(UserOnlineDTO dto) {
        User_Online entity = dtoConvertor.convertDTOToEntity(dto);

        if (!repository.existsById(dto.getUuid())) {
            throw new NotResourceException("UserOnline not exists");
        }

        return repository.save(entity);
    }

    @Override
    @CacheEvict(value = "user-online",key = "#uuid")
    public void deleteById(UUID uuid) {
        if(!repository.existsById(uuid)) {
            throw new NotResourceException("UserOnline does not exist");
        }

        repository.deleteByUuid(uuid);
    }

}
