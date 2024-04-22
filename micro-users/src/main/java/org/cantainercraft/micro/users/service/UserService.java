package org.cantainercraft.micro.users.service;

import org.cantainercraft.micro.users.dto.UserDTO;
import org.cantainercraft.micro.users.dto.UserUpdateDTO;
import org.cantainercraft.project.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
     List<User> findAll();

     User findById(Long id);

     User save(UserDTO userDTO);

     boolean update(UserUpdateDTO userUpdateDTO);

     Optional<User> findByEmail(String email);

     List<User> findBySearch(String email,String password);

     Optional<User> findByEmailAndPassword(String email,String pass);
     void deleteById(Long id);

     void deleteByEmail(String email);
}
