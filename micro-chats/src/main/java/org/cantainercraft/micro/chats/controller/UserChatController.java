package org.cantainercraft.micro.chats.controller;

import lombok.RequiredArgsConstructor;
import org.cantainercraft.micro.chats.service.UserChatService;
import org.cantainercraft.micro.chats.webflux.UserWebClient;
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
    private final UserWebClient userWebClient;
    private final UserChatService userChatService;


    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("/all")
    public ResponseEntity<List<User_Chat>> findAll(){
        return ResponseEntity.ok(userChatService.findAll());
    }

    @PostMapping("/id")
    public ResponseEntity<User_Chat> findById(@RequestBody Long id){
        Optional<User_Chat> chat = userChatService.findById(id);

        if(chat.isEmpty()) {
            throw new NotResourceException("No content");
        }
        return ResponseEntity.ok(chat.get());
    }

    @PostMapping("/search")
    public ResponseEntity<List<User_Chat>> findBySearch(@RequestBody UserChatSearchDTO dto){

        Long id = dto.getId();
        Long userId = dto.getUserId();
        UUID chatId = dto.getChatId();
        return ResponseEntity.ok(userChatService.findBySearch(id,userId,chatId));
    }

    @PutMapping("/delete")
    public ResponseEntity<Boolean> delete(@RequestBody Long id){
            Optional<User_Chat> userChat= userChatService.findById(id);

            if(userChat.isEmpty()){
                throw new NotResourceException("No content for delete");
            }

            userChatService.deleteById(id);
            return ResponseEntity.ok(true);
    }

    @PutMapping("/delete/user")
    public ResponseEntity<Integer> delete(@RequestBody UserChatDTO dto){
            List<User_Chat> user_chats = userChatService.findBySearch(null, dto.getUserId(),dto.getChat().getUuid());

            if(user_chats.isEmpty()){
                throw new NotResourceException("No content for delete");
            }

            return ResponseEntity.ok(userChatService.deleteByUserId(dto.getUserId(),dto.getChat().getUuid()));
    }

    @PostMapping("/add")
    public ResponseEntity<User_Chat> save(@RequestBody UserChatDTO userChatDTO){
        System.out.println("add user:"+userChatDTO.getUserId());

        System.out.println(userWebClient.userExist(userChatDTO.getUserId()));
        if(!userWebClient.userExist(userChatDTO.getUserId())){
            throw new NotResourceException("user is not exist");
        }

        return ResponseEntity.ok(userChatService.save(userChatDTO));
    }


    @PutMapping("/update")
    public ResponseEntity<User_Chat> update(@RequestBody UserChatDTO userChatDTO){
        Optional<User_Chat> userChat= userChatService.findById(userChatDTO.getId());
        if(userChat.isEmpty()){
            throw new NotResourceException("No content for update");
        }

        if(userWebClient.userExist(userChatDTO.getUserId()) == null){
            throw new NotResourceException("user is not exist");
        }

        return ResponseEntity.ok(userChatService.update(userChatDTO));
    }
}
