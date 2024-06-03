package org.cantainercraft.micro.users.service.impl;

import lombok.RequiredArgsConstructor;
import org.cantainercraft.micro.users.convertor.UserOnlineDTOConvertor;
import org.cantainercraft.micro.users.dto.UserOnlineDTO;
import org.cantainercraft.micro.users.repository.UserOnlineRepository;
import org.cantainercraft.micro.users.service.UserOnlineService;
import org.cantainercraft.micro.utilits.exception.ExistResourceException;
import org.cantainercraft.micro.utilits.exception.NotResourceException;
import org.cantainercraft.project.entity.users.User_Online;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class UserOnlineServiceImpl implements UserOnlineService {
    private final UserOnlineRepository userOnlineRepository;
    private final UserOnlineDTOConvertor userOnlineDTOConvertor;

    @Override
    public List<User_Online> findAll() {

        return userOnlineRepository.findAll();
    }

    @Override
    public Optional<User_Online> findById(UUID uuid) {
        Optional<User_Online> userOnline = userOnlineRepository.findById(uuid);
        if (userOnline.isEmpty()) {
            throw new NotResourceException("User online not found");
        }

        return userOnlineRepository.findById(uuid);
    }


    @Override
    public User_Online save(UserOnlineDTO dto) {

        User_Online userOnline = userOnlineDTOConvertor.convertDTOToEntity(dto);
        if (userOnlineRepository.existsByUserId(dto.getUser().getId())) {
            throw new ExistResourceException("UserOnline already exists");
        }

        return userOnlineRepository.save(userOnline);
    }

    @Override
    public User_Online update(UserOnlineDTO userOnlineDTO) {
        User_Online userOnline = userOnlineDTOConvertor.convertDTOToEntity(userOnlineDTO);
        Optional<User_Online> userOnlineOptional = userOnlineRepository.findById(userOnlineDTO.getUuid());
        if (userOnlineOptional.isEmpty()) {
            throw new NotResourceException("UserOnline already exists");
        }

        return userOnlineRepository.save(userOnline);
    }

    @Override
    public void deleteById(UUID uuid) {
        Optional<User_Online> userOnlineOptional = userOnlineRepository.findById(uuid);
        if(userOnlineOptional.isEmpty()) {
            throw new NotResourceException("UserOnline does not exist");
        }

    userOnlineRepository.deleteByUuid(uuid);
    }

}
