package org.cantainercraft.micro.users.service.impl;

import lombok.RequiredArgsConstructor;
import org.cantainercraft.micro.users.convertor.UserDTOConvertor;
import org.cantainercraft.micro.users.dto.CustomUserDetails;
import org.cantainercraft.micro.users.service.InitService;
import org.cantainercraft.micro.users.service.UserService;
import org.cantainercraft.project.entity.Profile;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.cantainercraft.micro.users.dto.UserDTO;
import org.cantainercraft.project.entity.User;
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
    public Optional<User> findById(Long id){
        return userRepository.findById(id);
    }

    @Override
    public User save(UserDTO userDTO){
        User user = userDTOConvertor.convertUserDTOToUser(userDTO);
        Profile profile = Profile.builder().user(user).build();
        profileInitService.init(profile);
        return userRepository.save(user);
    }

    @Override
    public boolean update(UserDTO userDTO){
        User user = userDTOConvertor.convertUserDTOToUser(userDTO);
        userRepository.save(user);
        return true;
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
    public void deleteById(Long id){
        userRepository.deleteById(id);
    }

    @Override
    public void deleteByEmail(String email){
        userRepository.deleteByEmail(email);
    }


    @Override
    public Optional<User> findByUsername(String username){
        return userRepository.findByName(username);
    }

    @Override
    public boolean existByUsername(String username){
        return userRepository.existsByName(username);
    }

    @Override
    public boolean existByEmail(String email){
        return userRepository.existsByEmail(email);
    }


}
