package ru.javabegin.backend.todo.eurekaclient.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.javabegin.backend.todo.eurekaclient.convertor.UserDTOConvertor;
import ru.javabegin.backend.todo.eurekaclient.dto.UserDTO;
import ru.javabegin.backend.todo.eurekaclient.dto.UserUpdateDTO;
import ru.javabegin.backend.todo.eurekaclient.entity.User;
import ru.javabegin.backend.todo.eurekaclient.repository.UserRepository;

import java.util.List;
import java.util.NoSuchElementException;

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
        return  true;
    }
    
    public User findByEmail(String email){
        return userRepository.findByEmail(email).get();
    }
    
    public void delete(Long id){
        userRepository.deleteById(id);
    }
}
