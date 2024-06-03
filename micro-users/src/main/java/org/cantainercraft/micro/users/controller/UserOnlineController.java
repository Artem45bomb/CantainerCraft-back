package org.cantainercraft.micro.users.controller;


import lombok.RequiredArgsConstructor;
import org.cantainercraft.micro.users.dto.UserOnlineDTO;
import org.cantainercraft.micro.users.service.UserOnlineService;
import org.cantainercraft.micro.utilits.exception.MessageError;
import org.cantainercraft.micro.utilits.exception.NotResourceException;
import org.cantainercraft.project.entity.users.User_Online;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;


@RestController
@RequiredArgsConstructor
public class UserOnlineController {


    private final UserOnlineService userOnlineService;

    @GetMapping("/uuid")
    public ResponseEntity<Optional<User_Online>> findById(@RequestBody UUID uuid) {
        return ResponseEntity.ok(userOnlineService.findById(uuid));
    }

    @PostMapping("/add")
    public ResponseEntity<User_Online> save(@RequestBody UserOnlineDTO userOnlineDTO){
         return ResponseEntity.ok(userOnlineService.save(userOnlineDTO));
    }


    @PutMapping("/update")
    public ResponseEntity<Boolean> update(@RequestBody UserOnlineDTO userOnlineDTO){
        return new ResponseEntity(MessageError.of("profile is not exist"), HttpStatus.NOT_ACCEPTABLE);
    }


    @DeleteMapping("/delete/id")
    public ResponseEntity<Boolean> deleteById(@RequestBody UUID uuid ){
        return new ResponseEntity(MessageError.of("profile is not exist"),HttpStatus.NOT_ACCEPTABLE);
    }

}
