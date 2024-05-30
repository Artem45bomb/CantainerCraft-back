package org.cantainercraft.micro.users.service.impl;

import lombok.RequiredArgsConstructor;
import org.cantainercraft.micro.users.convertor.UserOnlineDTOConvertor;
import org.cantainercraft.micro.users.dto.UserOnlineDTO;
import org.cantainercraft.micro.users.repository.UserOnlineRepository;
import org.cantainercraft.micro.users.service.UserOnlineService;
import org.cantainercraft.micro.utilits.exception.ExistResourceException;
import org.cantainercraft.project.entity.users.User_Online;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

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
    public Optional<User_Online> findById(int id) {
        return userOnlineRepository.findById(id);
    }



    @Override
    public User_Online save(UserOnlineDTO dto) {

        if (userOnlineRepository.existsByUserId(dto.getUser().getId())) {
            throw new ExistResourceException("UserOnline already exists");
        }
        else {
            User_Online userOnline = userOnlineDTOConvertor.convertUserOnlineDTOtoUserOnline(dto);
            return userOnlineRepository.save(userOnline);
        }
    }

    @Override
    public void update(UserOnlineDTO UserOnlineDTO) {

    }

    @Override
    public void deleteById(int id) {

        userOnlineRepository.deleteById(id);
    }
}
