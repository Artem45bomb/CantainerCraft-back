package ru.javabegin.backend.todo.eurekaclient.controller;

import jakarta.ws.rs.PUT;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.javabegin.backend.todo.eurekaclient.dto.UserDTO;
import ru.javabegin.backend.todo.eurekaclient.dto.UserUpdateDTO;
import ru.javabegin.backend.todo.eurekaclient.entity.User;
import ru.javabegin.backend.todo.eurekaclient.service.UserService;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> findById(@RequestBody Long id){
        return ResponseEntity.ok(userService.findById(id));
    }

    @PostMapping("/email")
    public ResponseEntity<User> findByEmail(@RequestBody String email){
        if(email ==null || !email.trim().isEmpty()){
            return new ResponseEntity("email is null", HttpStatus.NOT_ACCEPTABLE);
        }
        return ResponseEntity.ok(userService.findByEmail(email));
    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> findAll(){
        return ResponseEntity.ok(userService.findAll());
    }

    @PostMapping("/create")
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

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id ){
        try{
            findById(id);
            userService.delete(id);
            return ResponseEntity.ok(true);
        }
        catch (NoSuchElementException exception){
            return new ResponseEntity("element is not exist",HttpStatus.NOT_ACCEPTABLE);
        }
    }
}
