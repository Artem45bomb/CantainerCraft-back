package org.cantainercraft.micro.users.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cantainercraft.micro.users.convertor.UserDTOConvertor;
import org.cantainercraft.micro.users.service.UserService;
import org.cantainercraft.micro.utilits.exception.ExistResourceException;
import org.cantainercraft.micro.utilits.exception.NotResourceException;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.cantainercraft.micro.users.dto.UserDTO;
import org.cantainercraft.project.entity.users.User;
import org.cantainercraft.micro.users.repository.UserRepository;

import java.util.List;
import java.util.Optional;


@Slf4j
@Component
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserDTOConvertor convertor;
    private final UserRepository repository;

    @Override
    public List<User> findAll(){
        return repository.findAll();
    }

    @Override
    @Cacheable(value = "users",key = "#id")
    public Optional<User> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public User save(UserDTO dto){
        if(existByUsername(dto.getUsername())){
            throw new ExistResourceException("user is exist");
        }
        User user = convertor.convertDTOToEntity(dto);

        return repository.save(user);
    }

    @Override
    @CachePut(value = "users",key = "#dto.id")
    public User update(UserDTO dto){
        User user = convertor.convertDTOToEntity(dto);
       
        if(!repository.existsById(dto.getId())) {
            throw new NotResourceException("user is not exist");
        }
        return repository.save(user);
    }

    @Override
    public Optional<User> findByEmail(String email){
        return repository.findByEmail(email);
    }

    @Override
    public List<User> findBySearch(String email,String password){
        return repository.findBySearch(email,password);
    }

    @Override
    @CacheEvict(value = "users",key = "#id")
    public void deleteById(Long id){
        if(!repository.existsById(id)) throw new NotResourceException("user is not exist");

        repository.deleteById(id);
    }

    @Override
    @CacheEvict(value = "users",allEntries = true)
    public void deleteByEmail(String email){
        if(!repository.existsByEmail(email)) throw new NotResourceException("user is not exist");

        repository.deleteByEmail(email);
    }


    @Override
    public Optional<User> findByUsername(String username){
        return repository.findByUsername(username);
    }

    @Override
    public boolean existByUsername(String username){
        return repository.existsByUsername(username);
    }

    @Override
    public boolean existById(Long id){
        return repository.existsById(id);
    }


}
