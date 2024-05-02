package org.cantainercraft.micro.chats.controller;

import lombok.RequiredArgsConstructor;
import org.cantainercraft.micro.chats.service.MessageService;
import org.cantainercraft.micro.utilits.exception.ExistResourceException;
import org.cantainercraft.micro.utilits.exception.NotResourceException;
import org.cantainercraft.micro.utilits.exception.NotValidateParamException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.cantainercraft.micro.chats.dto.MessageDTO;
import org.cantainercraft.micro.chats.dto.MessageSearchDTO;
import org.cantainercraft.micro.chats.feign.UserFeignClient;
import org.cantainercraft.project.entity.chats.Message;


import java.util.*;

@RestController
@RequestMapping("/message")
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;
    private final UserFeignClient userFeignClient;

    @PostMapping("/all")
    public ResponseEntity<List<Message>> findAll(){
        return ResponseEntity.ok(messageService.findAll());
    }


    @PostMapping("/uuid")
    public ResponseEntity<Message> findByUUID(@RequestBody UUID uuid){
        Optional<Message> message = messageService.findByUUID(uuid);

        if(message.isEmpty()) {
            throw new NotResourceException("No content");
        }
        return ResponseEntity.ok(message.get());
    }


    @PostMapping("/add")
    public ResponseEntity<Message> save(@RequestBody MessageDTO messageDTO) {

        if (userFeignClient.userExist(messageDTO.getUserId()).getBody() == null) {
            throw new NotResourceException("user is not exist");
        }
        Optional<Message> message = messageService.findByUUID(messageDTO.getUuid());

        if (message.isPresent()) {
            throw new ExistResourceException("Message is exist");
        }
        if (messageDTO.getUuid() == null) {
            throw new NotValidateParamException("Missed param: id");
        }
        return ResponseEntity.ok(messageService.save(messageDTO));
    }

    @PutMapping("/delete")
    public ResponseEntity<Boolean> delete(@RequestBody UUID uuid){
            Optional<Message> message = messageService.findByUUID(uuid);

            if(message.isEmpty()){
                throw new NotResourceException("No content for delete");
            }

            return ResponseEntity.ok(messageService.delete(uuid));
    }


    @PutMapping("/update")
    public ResponseEntity<Message> update(@RequestBody MessageDTO messageDTO) {
            Optional<Message> message = messageService.findByUUID(messageDTO.getUuid());
            if(message.isEmpty()) {
                throw new NotResourceException("No content for update");
            }
            if(messageDTO.getUuid() == null) {
                throw new NotValidateParamException("Missed param: id");
            }
            if (userFeignClient.userExist(messageDTO.getUserId()) == null) {
                throw new NotResourceException("user is not exist");
            }
            return ResponseEntity.ok(messageService.update(messageDTO));

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

