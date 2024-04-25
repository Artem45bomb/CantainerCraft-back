package org.cantainercraft.micro.chats.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.cantainercraft.micro.chats.dto.UserChatDTO;
import org.cantainercraft.micro.chats.dto.UserChatSearchDTO;
import org.cantainercraft.micro.chats.feign.UserFeignClient;
import org.cantainercraft.micro.chats.service.impl.UserChatServiceImpl;
import org.cantainercraft.project.entity.chats.User_Chat;

import java.util.*;

@RestController
@RequestMapping("/user_chat")
@RequiredArgsConstructor
class UserChatController {
    //UserFeignClient для взаимодействия с micro-users
    private final UserFeignClient userFeignClient;
    private final UserChatServiceImpl userChatService;

    @PostMapping("/all")
    public ResponseEntity<List<User_Chat>> findAll(){
        return ResponseEntity.ok(userChatService.findAll());
    }


    @PostMapping("/id")
    public ResponseEntity<User_Chat> findById(@RequestBody Long id){
        Optional<User_Chat> chat = userChatService.findById(id);

        if(chat.isPresent()) {
            return ResponseEntity.ok(chat.get());
        }
        return new ResponseEntity("No content", HttpStatus.NO_CONTENT);
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
        try{
            Optional<User_Chat> userChat= userChatService.findById(id);
            if(userChat.isEmpty()){
                throw new NoSuchElementException();
            }
            userChatService.deleteById(id);
            return ResponseEntity.ok(true);
        }
        catch (NoSuchElementException exception){
            return new ResponseEntity("No content for delete",HttpStatus.NON_AUTHORITATIVE_INFORMATION);
        }
    }

    @PutMapping("/delete/user")
    public ResponseEntity<Integer> delete(@RequestBody UserChatDTO dto){
            List<User_Chat> user_chats = userChatService.findBySearch(null, dto.getUserId(),dto.getChat().getUuid());
            if(!user_chats.isEmpty()){
                return ResponseEntity.ok(userChatService.deleteByUserId(dto.getUserId(),dto.getChat().getUuid()));
            }
            return new ResponseEntity("No content for delete",HttpStatus.NON_AUTHORITATIVE_INFORMATION);
    }

    @PostMapping("/add")
    public ResponseEntity<User_Chat> save(@RequestBody UserChatDTO userChatDTO){


        if(userFeignClient.userExist(userChatDTO.getUserId()).getBody() != null){
            return ResponseEntity.ok(userChatService.save(userChatDTO));
        }

        return new ResponseEntity("user is not exist",HttpStatus.NOT_ACCEPTABLE);

    }

    @PutMapping("/update")
    public ResponseEntity<User_Chat> update(@RequestBody UserChatDTO userChatDTO){
        try{
            Optional<User_Chat> userChat= userChatService.findById(userChatDTO.getId());
            if(userChat.isEmpty()){
                throw new NoSuchElementException();
            }

            if(userFeignClient.userExist(userChatDTO.getUserId()).getBody() != null){
                return ResponseEntity.ok(userChatService.update(userChatDTO));
            }

            return new ResponseEntity("user is not exist",HttpStatus.NOT_ACCEPTABLE);
        }
        catch (NoSuchElementException exception){
            return new ResponseEntity("No content for delete",HttpStatus.NON_AUTHORITATIVE_INFORMATION);
        }
    }
}
