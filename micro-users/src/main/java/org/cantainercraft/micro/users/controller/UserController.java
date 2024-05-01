package org.cantainercraft.micro.users.controller;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.cantainercraft.micro.utilits.exception.ExistResourceException;
import org.cantainercraft.micro.utilits.exception.MessageError;
import org.cantainercraft.micro.utilits.exception.NotResourceException;
import org.cantainercraft.micro.utilits.exception.NotValidateParamException;
import org.modelmapper.ValidationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.cantainercraft.micro.users.dto.UserDTO;
import org.cantainercraft.micro.users.dto.UserSearchDTO;
import org.cantainercraft.project.entity.User;
import org.cantainercraft.micro.users.service.UserService;
import java.util.*;


@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final Gson gson = new Gson();
    private final UserService userService;


    @PostMapping("/id")
    public ResponseEntity<User> findById(@RequestBody Long id){
        Optional<User> user = userService.findById(id);

        if(user.isEmpty()){
            throw new NotResourceException("user is not exist");
        }

        return ResponseEntity.ok(user.get());
    }

    @PostMapping("/search")
    public ResponseEntity<List<User>> findBySearch(@RequestBody UserSearchDTO userSearchDTO){

        String email = userSearchDTO.getEmail() == null ? null : userSearchDTO.getEmail();
        String password = userSearchDTO.getPassword() ==null ? null :userSearchDTO.getPassword();

        return ResponseEntity.ok(userService.findBySearch(email,password));
    }

    @PostMapping("/email")
    public ResponseEntity<User> findByEmail(@RequestBody String email){
        if(email ==null || !email.trim().isEmpty()){
            throw  new NotValidateParamException("email is null");
        }
        Optional<User> user = userService.findByEmail(email);

        return user.map(ResponseEntity::ok)
                .orElseGet(() -> {throw new NotResourceException("user is not exist");});

    }

    @PostMapping("/name")
    public ResponseEntity<User> findByName(@RequestBody String name){
        if(name ==null || !name.trim().isEmpty()){
            throw  new NotValidateParamException("email is null");
        }
        Optional<User> user = userService.findByEmail(name);

        if(user.isEmpty()){
            throw new NotResourceException("user is not exist");
        }

        return ResponseEntity.ok(user.get());

    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_ADMIN_TEMP')")
    @GetMapping("/all")
    public ResponseEntity<List<User>> findAll(){
        return ResponseEntity.ok(userService.findAll());
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("/add")
    public ResponseEntity<User> save(@RequestBody UserDTO userDTO){
        Optional<User> user = userService.findByEmail(userDTO.getEmail());

        if(user.isPresent()){
            throw new ExistResourceException("user is exist");
        }

        if(userDTO.getId() != null ){
            throw new NotValidateParamException("missed param:id");
        }

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userService.save(userDTO));
    }

    @PreAuthorize("hasAnyRole('ADMIN','ADMIN_TEMP')")
    @PutMapping("/update")
    public ResponseEntity<String> update(@RequestBody UserDTO dto){
            if(dto.getId() == null){
                throw new NotValidateParamException("missed param: id");
            }

            Optional<User> user = userService.findById(dto.getId());

            if(user.isEmpty())  throw new NotResourceException("user is not exist");

            userService.update(dto);
            return ResponseEntity.ok("user update");
    }

    @PreAuthorize("hasAnyRole('ADMIN','ADMIN_TEMP')")
    @PutMapping("/delete/email")
    public ResponseEntity<String> deleteByEmail(@RequestBody String email){
            Optional<User> user = userService.findByEmail(email);

            if(user.isEmpty()) throw new NotResourceException("user is not exist");;

            userService.deleteByEmail(email);
            return ResponseEntity.ok("delete user");
    }

    @PreAuthorize("hasAnyRole('ADMIN','ADMIN_TEMP')")
    @PutMapping("/delete/id")
    public ResponseEntity<String> deleteById(@RequestBody Long id ){
            Optional<User> user= userService.findById(id);

            if(user.isEmpty())  throw new NotResourceException("user is not exist");

            userService.deleteById(id);
            return ResponseEntity.ok("delete user");
    }
}
