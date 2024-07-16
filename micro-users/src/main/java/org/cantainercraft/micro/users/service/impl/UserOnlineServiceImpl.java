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
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class UserOnlineServiceImpl implements UserOnlineService {
    private final UserOnlineRepository userOnlineRepository;
    private final UserOnlineDTOConvertor dtoConvertor;

    @Override
    public List<User_Online> findAll() {
        return userOnlineRepository.findAll();
    }

    @Override
    @Cacheable(value = "user-online",key = "#uuid")
    public User_Online findById(UUID uuid) {
        Optional<User_Online> userOnline = userOnlineRepository.findById(uuid);
        if (userOnline.isEmpty()) {
            throw new NotResourceException("User online not found");
        }

        return userOnline.get();
    }

    @Override
    public User_Online save(UserOnlineDTO dto) {
        User_Online userOnline = dtoConvertor.convertDTOToEntity(dto);
        if (userOnlineRepository.existsByUserId(dto.getUser().getId())) {
            throw new ExistResourceException("UserOnline already exists");
        }

        return userOnlineRepository.save(userOnline);
    }

    @Override
    @CachePut(value = "user-online",key = "#dto.uuid")
    public User_Online update(UserOnlineDTO dto) {
        User_Online entity = dtoConvertor.convertDTOToEntity(dto);
        Optional<User_Online> userOnline = userOnlineRepository.findById(dto.getUuid());
        if (userOnline.isEmpty()) {
            throw new NotResourceException("UserOnline already exists");
        }

        return userOnlineRepository.save(entity);
    }

    @Override
    @CacheEvict(value = "user-online",key = "#uuid")
    public void deleteById(UUID uuid) {
        Optional<User_Online> userOnline = userOnlineRepository.findById(uuid);
        if(userOnline.isEmpty()) {
            throw new NotResourceException("UserOnline does not exist");
        }

        userOnlineRepository.deleteByUuid(uuid);
    }

}
