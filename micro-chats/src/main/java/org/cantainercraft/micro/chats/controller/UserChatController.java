package org.cantainercraft.micro.chats.controller;

import lombok.RequiredArgsConstructor;
import org.cantainercraft.micro.chats.service.UserChatService;
import org.cantainercraft.micro.utilits.exception.NotResourceException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.cantainercraft.micro.chats.dto.UserChatDTO;
import org.cantainercraft.micro.chats.dto.UserChatSearchDTO;
import org.cantainercraft.project.entity.chats.User_Chat;

import java.util.*;

@RestController
@RequestMapping("/api/user_chat")
@RequiredArgsConstructor
class UserChatController {
    //UserFeignClient для взаимодействия с micro-users
    private final UserChatService service;


    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("/all")
    public ResponseEntity<List<User_Chat>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @PostMapping("/id")
    public ResponseEntity<User_Chat> findById(@RequestBody Long id){
        Optional<User_Chat> userChat = service.findById(id);

        if(userChat.isEmpty()) throw new  NotResourceException("content is not exist");

        return ResponseEntity.ok(userChat.get());
    }

    @PostMapping("/search")
    public ResponseEntity<List<User_Chat>> findBySearch(@RequestBody UserChatSearchDTO dto){
        return ResponseEntity.ok(service.findBySearch(dto.getUserId(), dto.getChatId()));
    }

    @PutMapping("/delete")
    public void deleteById(@RequestBody Long id){
        service.deleteById(id);
    }

    @PutMapping("/delete/user")
    public void delete(@RequestBody UserChatDTO dto){
        service.deleteByUserId(dto.getUserId(),dto.getChat().getUuid());
    }

    @PostMapping("/add")
    public ResponseEntity<User_Chat> save(@RequestBody UserChatDTO dto){
        return ResponseEntity.ok(service.save(dto));
    }


    @PutMapping("/update")
    public ResponseEntity<User_Chat> update(@RequestBody UserChatDTO dto){
        return ResponseEntity.ok(service.update(dto));
    }
}
