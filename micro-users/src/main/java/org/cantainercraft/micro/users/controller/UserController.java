package org.cantainercraft.micro.users.controller;

import com.google.gson.Gson;
import org.cantainercraft.micro.users.exception.MessageError;
import org.cantainercraft.micro.users.service.ProfileService;
import org.cantainercraft.project.entity.Profile;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.cantainercraft.micro.users.dto.UserDTO;
import org.cantainercraft.micro.users.dto.UserSearchDTO;
import org.cantainercraft.project.entity.User;
import org.cantainercraft.micro.users.service.UserService;

import javax.swing.text.html.Option;
import java.util.*;


@RestController
@RequestMapping("/user")
public class UserController {
    private final Gson gson = new Gson();
    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/id")
    public ResponseEntity<User> findById(@RequestBody Long id){
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type","application/json;charset=UTF-8");

        Optional<User> user = userService.findById(id);

        if(user.isPresent()){
            return ResponseEntity.ok(user.get());
        }


        return new ResponseEntity(MessageError.of("user is not exist"),headers,HttpStatus.NOT_FOUND);
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
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type","application/json");
        Optional<User> user = userService.findByEmail(userDTO.getEmail());

        if(user.isPresent()){
            return new ResponseEntity(MessageError.of("user is exist"),headers,HttpStatus.CONFLICT);
        }

        if(userDTO.getId() != null ){
            return new ResponseEntity(MessageError.of("missed param:id"),headers,HttpStatus.NO_CONTENT);
        }


        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userService.save(userDTO));
    }

    @PutMapping("/update")
    public ResponseEntity<String> update(@RequestBody UserDTO dto){
        try{

            if(dto.getId() == null){
                return new ResponseEntity(MessageError.of("missed param: id"),HttpStatus.NOT_FOUND);
            }

            Optional<User> user = userService.findById(dto.getId());
            user.orElseGet(() -> {throw new NoSuchElementException();});
            userService.update(dto);
            return ResponseEntity.ok("user update");
        }
        catch (NoSuchElementException exception){
            return new ResponseEntity(MessageError.of("user is not exist"),HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @PutMapping("/delete/email")
    public ResponseEntity<String> deleteByEmail(@RequestBody String email){
        try{
            User user = userService.findByEmail(email)
                    .orElseGet(() ->{throw new NoSuchElementException();});
            userService.deleteByEmail(email);
            return ResponseEntity.ok("delete user");
        }
        catch (NoSuchElementException exception){
            return new ResponseEntity("user is not exist",HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @PutMapping("/delete/id")
    public ResponseEntity<String> deleteById(@RequestBody Long id ){
        try{
            Optional<User> user= userService.findById(id);

            if(user.isEmpty()) throw new NoSuchElementException();

            userService.deleteById(id);
            return ResponseEntity.ok("delete user");
        }
        catch (NoSuchElementException exception){
            return new ResponseEntity("user is not exist",HttpStatus.NOT_ACCEPTABLE);
        }
    }
}
