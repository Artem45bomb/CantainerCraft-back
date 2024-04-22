package org.cantainercraft.micro.users.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.cantainercraft.micro.users.dto.UserDTO;
import org.cantainercraft.micro.users.dto.UserSearchDTO;
import org.cantainercraft.micro.users.dto.UserUpdateDTO;
import org.cantainercraft.project.entity.User;
import org.cantainercraft.micro.users.service.UserService;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/id")
    public ResponseEntity<User> findById(@RequestBody Long id){
        try {
            return ResponseEntity.ok(userService.findById(id));
        }
        catch (NoSuchElementException exception){
            return new ResponseEntity("user is not exist",HttpStatus.NO_CONTENT);
        }
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
            return new ResponseEntity("email is null", HttpStatus.NOT_ACCEPTABLE);
        }
        Optional<User> user = userService.findByEmail(email);

        return user.map(ResponseEntity::ok)
                .orElseGet(() -> new ResponseEntity("user is not exist", HttpStatus.NO_CONTENT));

    }


    @GetMapping("/all")
    public ResponseEntity<List<User>> findAll(){
        return ResponseEntity.ok(userService.findAll());
    }

    @PostMapping("/add")
    public ResponseEntity<User> save(@RequestBody UserDTO userDTO){
        return ResponseEntity.ok(userService.save(userDTO));
    }



    @PutMapping
    public ResponseEntity<Boolean> update(@RequestBody UserUpdateDTO userUpdateDTO){
        try{
            findById(userUpdateDTO.getId());
            userService.update(userUpdateDTO);
            return ResponseEntity.ok(true);
        }
        catch (NoSuchElementException exception){
            return new ResponseEntity("element is not exist",HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @PutMapping("/delete/email")
    public ResponseEntity<Boolean> deleteByEmail(@RequestBody String email){
        try{
            User user = userService.findByEmail(email)
                    .orElseGet(() -> {throw new NoSuchElementException();});
            userService.deleteByEmail(email);
            return ResponseEntity.ok(true);
        }
        catch (NoSuchElementException exception){
            return new ResponseEntity("element is not exist",HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteById(@PathVariable Long id ){
        try{
            userService.findById(id);
            userService.deleteById(id);
            return ResponseEntity.ok(true);
        }
        catch (NoSuchElementException exception){
            return new ResponseEntity("element is not exist",HttpStatus.NOT_ACCEPTABLE);
        }
    }
}
