package ru.micro.chats.data.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.micro.chats.data.service.ChatService;
import ru.micro.chats.data.dto.ChatUpdateDTO;
import ru.weather.project.entity.chats.Chat;

import java.util.*;

@RestController
@RequestMapping("/chat")
public class ChatController {
    
    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @PostMapping("/all")
    public ResponseEntity<List<Chat>> findAll(){
        return ResponseEntity.ok(chatService.findAll());
    }


    @PostMapping("/uuid")
    public ResponseEntity<Chat> findByUUID(@RequestBody UUID uuid){
            Optional<Chat> chat = chatService.findByUUID(uuid);
            
            if(chat.isPresent()) {
                return ResponseEntity.ok(chat.get());
            }
            return new ResponseEntity("No content", HttpStatus.NON_AUTHORITATIVE_INFORMATION);
    }
    
    @PostMapping("/name")
    public ResponseEntity<Chat> findByName(@RequestBody String name){
        Optional<Chat> chat = chatService.findByName(name);

        if(chat.isPresent()) {
            return ResponseEntity.ok(chat.get());
        }
        return new ResponseEntity("No content", HttpStatus.NON_AUTHORITATIVE_INFORMATION);
    }
    
    @PutMapping("/delete")
    public ResponseEntity<Boolean> delete(@RequestBody UUID uuid){
        try{
            chatService.findByUUID(uuid);
            return ResponseEntity.ok(chatService.delete(uuid));
        }
        catch (NoSuchElementException exception){
            return new ResponseEntity("No content for delete",HttpStatus.NON_AUTHORITATIVE_INFORMATION);
        }
    }

    @PutMapping("/delete/name")
    public ResponseEntity<Boolean> delete(@RequestBody String name){
        try{
            chatService.findByName(name);
            return ResponseEntity.ok(chatService.deleteByName(name));
        }
        catch (NoSuchElementException exception){
            return new ResponseEntity("No content for delete",HttpStatus.NON_AUTHORITATIVE_INFORMATION);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<Boolean> update(@RequestBody ChatUpdateDTO chatUpdateDTO){
        try{
            chatService.findByUUID(chatUpdateDTO.getUuid());
            return ResponseEntity.ok(chatService.update(chatUpdateDTO));
        }
        catch (NoSuchElementException exception){
            return new ResponseEntity("No content for delete",HttpStatus.NON_AUTHORITATIVE_INFORMATION);
        }
    }

//    @PostMapping
//    public ResponseEntity<List<Chat>> findBySearch(@RequestBody ChatSearchDTO chatSearchDTO){
//
//        UUID uuid = chatSearchDTO.getUuid() == null ? null :chatSearchDTO.getUuid();
//        String chatName = chatSearchDTO.getChatName() == null ? null : chatSearchDTO.getChatName();
//        TypeChat typeChat = chatSearchDTO.getTypeChat() == null ? null : chatSearchDTO.getTypeChat();
//        Date dateStart = chatSearchDTO.getDateStart() == null ? null :chatSearchDTO.getDateStart();
//        Date dateEnd = chatSearchDTO.getDateEnd() == null ? null : chatSearchDTO.getDateEnd();
//
//        return ResponseEntity.ok(chatService.findBySearch(uuid,chatName,typeChat,dateStart,dateEnd));
//
//    }

}
