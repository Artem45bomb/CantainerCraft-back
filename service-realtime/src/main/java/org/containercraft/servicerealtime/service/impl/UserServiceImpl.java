package org.containercraft.servicerealtime.service.impl;

import lombok.RequiredArgsConstructor;
import org.cantainercraft.micro.utilits.exception.NotResourceException;
import org.cantainercraft.micro.utilits.exception.NotValidateParamException;
import org.containercraft.servicerealtime.convertor.UserDTOConvertor;
import org.containercraft.servicerealtime.dto.UserDTO;
import org.containercraft.servicerealtime.entity.User;
import org.containercraft.servicerealtime.repository.UserRepository;
import org.containercraft.servicerealtime.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository repository;
    private final UserDTOConvertor convertor;

    @Override
    public List<User> findAll() {
        return repository.findAll();
    }

    @Override
    public User findById(Long userId) {
        Optional<User> user = repository.findById(userId);

        if(user.isEmpty()) throw new NotResourceException("user is not exist");

        return user.get();
    }


    @Override
    public User save(UserDTO dto) {
        if(dto.id() != null) throw new NotValidateParamException("missed param: id");

        User user = convertor.convertDTOToEntity(dto);
        return repository.save(user);
    }

    @Override
    public User update(UserDTO dto) {
        if(!repository.existsById(dto.id()))  throw new NotResourceException("user is not exist");

        User user = convertor.convertDTOToEntity(dto);
        return repository.save(user);
    }

    @Override
    public void deleteById(Long userId) {
        if(!repository.existsById(userId))  throw new NotResourceException("user is not exist");

        repository.deleteById(userId);
    }
}
