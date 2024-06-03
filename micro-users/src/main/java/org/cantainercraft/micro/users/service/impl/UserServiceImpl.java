package org.cantainercraft.micro.users.service.impl;

import lombok.RequiredArgsConstructor;
import org.cantainercraft.micro.users.convertor.UserDTOConvertor;
import org.cantainercraft.micro.users.dto.ServiceUserDTO;
import org.cantainercraft.micro.users.service.InitService;
import org.cantainercraft.micro.users.service.UserService;
import org.cantainercraft.micro.utilits.exception.ExistResourceException;
import org.cantainercraft.micro.utilits.exception.NotResourceException;
import org.cantainercraft.project.entity.users.Profile;
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

@Component
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    
    private final UserDTOConvertor userDTOConvertor;
    private final InitService<Profile> profileInitService;
    private final UserRepository userRepository;

    @Override
    public List<User> findAll(){
        return userRepository.findAll();
    }

    @Override
    //@Cacheable(value = "users",key = "#id")
    public Optional<User> findById(Long id) {
        try {
            Thread.sleep(3000);
        }
        catch (InterruptedException ex){
            System.out.println(ex.getMessage());
        }
        return userRepository.findById(id);
    }

    @Override
    //@Cacheable(value = "users",key = "#userDTO.id")
    public User save(UserDTO userDTO){
        if(existByUsername(userDTO.getUsername())){
            throw new ExistResourceException("user is exist");
        }
        User user = userDTOConvertor.convertDTOToEntity(userDTO);

//        Profile profile = Profile
//                .builder()
//                .user(user)
//                .build();
//        profileInitService.init(profile);

        return userRepository.save(user);
    }

    @Override
    //@CachePut(value = "users",key = "#userDTO.id")
    public boolean update(UserDTO userDTO){
        User user = userDTOConvertor.convertDTOToEntity(userDTO);
        Optional<User> userOptional = userRepository.findById(userDTO.getId());
        if(userOptional.isEmpty()) {
            throw new NotResourceException("user is not exist");
        }
        userRepository.save(user);
        return true;
    }

    @Override
    public Optional<User> findByUsernameAndPassword(ServiceUserDTO dto){
        return userRepository.findByUsernameAndPassword(dto.getUsername(), dto.getPassword());
    }

    @Override
    public Optional<User> findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    @Override
    public List<User> findBySearch(String email,String password){
        return userRepository.findBySearch(email,password);
    }

    @Override
    //@CacheEvict("users")
    public void deleteById(Long id){
        userRepository.deleteById(id);
    }

    @Override
    //@CacheEvict("users")
    public void deleteByEmail(String email){
        userRepository.deleteByEmail(email);
    }


    @Override
    //@Cacheable(value = "users",key = "#username")
    public Optional<User> findByUsername(String username){
        return userRepository.findByUsername(username);
    }

    @Override
    public boolean existByUsername(String username){
        return userRepository.existsByUsername(username);
    }

    @Override
    public boolean existById(Long id){
        return userRepository.existsById(id);
    }


}
