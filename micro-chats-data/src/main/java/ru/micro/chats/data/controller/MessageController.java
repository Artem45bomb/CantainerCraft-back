package ru.micro.chats.data.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.micro.chats.data.dto.MessageUpdateDTO;
import ru.micro.chats.data.service.MessageService;
import ru.weather.project.entity.chats.Message;


import java.util.*;

@RestController
@RequestMapping("/message")
public class MessageController {

    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping("/all")
    public ResponseEntity<List<Message>> findAll(){
        return ResponseEntity.ok(messageService.findAll());
    }


    @PostMapping("/uuid")
    public ResponseEntity<Message> findByUUID(@RequestBody UUID uuid){
        Optional<Message> message = messageService.findByUUID(uuid);

        if(message.isPresent()) {
            return ResponseEntity.ok(message.get());
        }
        return new ResponseEntity("No content", HttpStatus.NON_AUTHORITATIVE_INFORMATION);
    }


    @PutMapping("/delete")
    public ResponseEntity<Boolean> delete(@RequestBody UUID uuid){
        try{
            messageService.findByUUID(uuid);
            return ResponseEntity.ok(messageService.delete(uuid));
        }
        catch (NoSuchElementException exception){
            return new ResponseEntity("No content for delete",HttpStatus.NON_AUTHORITATIVE_INFORMATION);
        }
    }


    @PutMapping("/update")
    public ResponseEntity<Boolean> update(@RequestBody MessageUpdateDTO messageUpdateDTO){
        try{
            messageService.findByUUID(messageUpdateDTO.getUuid());
            return ResponseEntity.ok(messageService.update(messageUpdateDTO));
        }
        catch (NoSuchElementException exception){
            return new ResponseEntity("No content for delete",HttpStatus.NON_AUTHORITATIVE_INFORMATION);
        }
    }

//    @PostMapping
//    public ResponseEntity<List<Message>> findBySearch(@RequestBody MessageSearchDTO messageSearchDTO){
//
//        UUID uuid = messageSearchDTO.getUuid() == null ? null :messageSearchDTO.getUuid();
//        String valueMessage = messageSearchDTO.getValue() == null ? null :messageSearchDTO.getValue();
//        Long userId = messageSearchDTO.getUserId() == null ? null : messageSearchDTO.getUserId();
//        Date dateStart = messageSearchDTO.getDateStart() == null ? null :messageSearchDTO.getDateStart();
//        Date dateEnd = messageSearchDTO.getDateEnd() == null ? null : messageSearchDTO.getDateEnd();
//
//        return ResponseEntity.ok(messageService.findBySearch(dateStart,dateEnd,valueMessage,uuid,userId));
//
//    }

}
