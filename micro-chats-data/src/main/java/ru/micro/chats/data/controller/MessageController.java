package ru.micro.chats.data.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.micro.chats.data.dto.MessageDTO;
import ru.micro.chats.data.dto.MessageSearchDTO;
import ru.micro.chats.data.feign.UserFeignClient;
import ru.micro.chats.data.service.MessageService;
import ru.weather.project.entity.chats.Message;


import java.util.*;

@RestController
@RequestMapping("/message")
public class MessageController {

    private final MessageService messageService;
    private final UserFeignClient userFeignClient;

    public MessageController(MessageService messageService, UserFeignClient userFeignClient) {
        this.messageService = messageService;
        this.userFeignClient = userFeignClient;
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
        return new ResponseEntity("No content", HttpStatus.NO_CONTENT);
    }


    @PostMapping("/add")
    public ResponseEntity<Message> save(@RequestBody MessageDTO messageDTO){

        if(userFeignClient.userExist(messageDTO.getUserId()).getBody() == null){
            return new ResponseEntity("user is not exist",HttpStatus.NO_CONTENT);
        }

        return ResponseEntity.ok(messageService.save(messageDTO));
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
    public ResponseEntity<Message> update(@RequestBody MessageDTO messageDTO){
        try{
            if(userFeignClient.userExist(messageDTO.getUserId()) == null){
                return new ResponseEntity("user is not exist",HttpStatus.NO_CONTENT);
            }

            messageService.findByUUID(messageDTO.getUuid());
            return ResponseEntity.ok(messageService.update(messageDTO));
        }
        catch (NoSuchElementException exception){
            return new ResponseEntity("No content for delete",HttpStatus.NON_AUTHORITATIVE_INFORMATION);
        }
    }

    @PostMapping
    public ResponseEntity<List<Message>> findBySearch(@RequestBody MessageSearchDTO messageSearchDTO){

        UUID uuid = messageSearchDTO.getUuid() == null ? null :messageSearchDTO.getUuid();
        String valueMessage = messageSearchDTO.getValue() == null ? null :messageSearchDTO.getValue();
        Long userId = messageSearchDTO.getUserId() == null ? null : messageSearchDTO.getUserId();
        
        Date dateStart = null;
        Date dateEnd = null;

        if (messageSearchDTO.getDateEnd() != null) {

            Calendar calendarEnd = Calendar.getInstance();
            calendarEnd.setTime(messageSearchDTO.getDateEnd());
            calendarEnd.set(Calendar.HOUR_OF_DAY, 23);
            calendarEnd.set(Calendar.MINUTE, 59);
            calendarEnd.set(Calendar.SECOND, 59);
            calendarEnd.set(Calendar.MILLISECOND, 999);

            dateEnd = calendarEnd.getTime(); // записываем конечную дату с 23:59

        }
        if (messageSearchDTO.getDateStart() != null) {

            Calendar calendarStart = Calendar.getInstance();
            calendarStart.setTime(messageSearchDTO.getDateEnd());
            calendarStart.set(Calendar.HOUR_OF_DAY, 0);
            calendarStart.set(Calendar.MINUTE, 0);
            calendarStart.set(Calendar.SECOND, 0);
            calendarStart.set(Calendar.MILLISECOND, 0);

            dateEnd = calendarStart.getTime(); // записываем конечную дату с 23:59

        }
        
        return ResponseEntity.ok(messageService.findBySearch(dateStart,dateEnd,valueMessage,uuid,userId));

    }

}
