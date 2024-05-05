package org.cantainercraft.micro.users.service;

import org.cantainercraft.micro.users.dto.UserDTO;
import org.cantainercraft.project.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface UserService {
     List<User> findAll();

     Optional<User> findById(Long id);

     User save(UserDTO userDTO);

     boolean update(UserDTO userUpdateDTO);

     Optional<User> findByEmail(String email);

     List<User> findBySearch(String email,String password);

     void deleteById(Long id);

     void deleteByEmail(String email);


     Optional<User> findByUsername(String username);

     boolean existByUsername(String username);

     boolean existByEmail(String email);

}
