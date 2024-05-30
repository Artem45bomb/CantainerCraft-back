package org.cantainercraft.micro.users.controller;


import lombok.RequiredArgsConstructor;
import org.cantainercraft.micro.users.dto.UserOnlineDTO;
import org.cantainercraft.micro.users.service.UserOnlineService;
import org.cantainercraft.micro.utilits.exception.MessageError;
import org.cantainercraft.micro.utilits.exception.NotResourceException;
import org.cantainercraft.project.entity.users.User_Online;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;



@RestController
@RequiredArgsConstructor
public class UserOnlineController {


    private final UserOnlineService userOnlineService;

    @PostMapping("/uuid")
    public ResponseEntity<Optional<User_Online>> findById(@RequestBody int id) {

        Optional<User_Online> userOnline = userOnlineService.findById(id);

        if (userOnline.isEmpty()) {
            throw new NotResourceException("UserOnline id not found");
        }
        return ResponseEntity.ok(userOnlineService.findById(id));
    }

    @PostMapping("/add")
    public ResponseEntity<User_Online> save(@RequestBody UserOnlineDTO userOnlineDTO){
        Optional<User_Online> userOnline = userOnlineService.findById(userOnlineDTO.getId());
        if (userOnline.isEmpty()) {
            throw new NotResourceException("UserOnline id not fonded");
        }
        
        else{
            return ResponseEntity.ok(userOnlineService.save(userOnlineDTO));
        }
    }


    @PutMapping("/update")
    public ResponseEntity<Boolean> update(@RequestBody UserOnlineDTO userOnlineDTO){
        Optional<User_Online> userOnline = userOnlineService.findById(userOnlineDTO.getId());
        if(userOnline.isPresent()){
            throw new NotResourceException("UserOnline id not fonded");
        }
        return new ResponseEntity(MessageError.of("profile is not exist"), HttpStatus.NOT_ACCEPTABLE);
    }


    @PutMapping("/delete/id")
    public ResponseEntity<Boolean> deleteById(@RequestBody int id ){
        Optional<User_Online> userOnline = userOnlineService.findById(id);
        if(userOnline.isPresent()){
            throw new NotResourceException("UserOnline id not fonded");
        }
        return new ResponseEntity(MessageError.of("profile is not exist"),HttpStatus.NOT_ACCEPTABLE);
    }

}
