package org.cantainercraft.micro.users.service;

import org.cantainercraft.micro.users.convertor.UserDTOConvertor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.cantainercraft.micro.users.dto.UserDTO;
import org.cantainercraft.micro.users.dto.UserUpdateDTO;
import org.cantainercraft.project.entity.User;
import org.cantainercraft.micro.users.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Component
@Transactional
public class UserService {
    
    private final UserDTOConvertor userDTOConvertor;
    private final UserRepository userRepository;
    
    public UserService(UserRepository userRepository,UserDTOConvertor userDTOConvertor) {
        this.userRepository = userRepository;
        this.userDTOConvertor =userDTOConvertor;
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public User findById(Long id){
        return userRepository.findById(id).get();
    }
    
    public User save(UserDTO userDTO){
        User user = userDTOConvertor.convertUserDTOToUser(userDTO);
        return userRepository.save(user);
    }
    
    public boolean update(UserUpdateDTO userUpdateDTO){
        User user = userDTOConvertor.convertUserDTOToUser(userUpdateDTO);
        user.setId(userUpdateDTO.getId());
        userRepository.save(user);
        return true;
    }

    public User findByEmail(String email){
        return userRepository.findByEmail(email).get();
    }

    public List<User> findBySearch(String email,String password){
        return userRepository.findBySearch(email,password);
    }

    public Optional<User> findByEmailAndPassword(String email,String pass){
        return userRepository.findByEmailAndPassword(email,pass);
    }
    public void delete(Long id){
        userRepository.deleteById(id);
    }
}
