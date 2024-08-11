package org.cantainercraft.micro.chats.controller;

import lombok.RequiredArgsConstructor;
import org.cantainercraft.micro.chats.service.MessageService;
import org.cantainercraft.micro.utilits.exception.NotResourceException;
import org.cantainercraft.micro.utilits.exception.NotValidateParamException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.cantainercraft.micro.chats.repository.dto.MessageDTO;
import org.cantainercraft.micro.chats.repository.dto.MessageSearchDTO;
import org.cantainercraft.micro.chats.feign.UserFeignClient;
import org.cantainercraft.project.entity.chats.Message;


import java.util.*;

@RestController
@RequestMapping("/api/message")
@RequiredArgsConstructor
public class MessageController {

    private final MessageService service;
    private final UserFeignClient userFeignClient;
    private static final String COLUM_ID = "id";

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("/all")
    public ResponseEntity<List<Message>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }


    @PostMapping("/uuid")
    public ResponseEntity<Message> findByUUID(@RequestBody UUID uuid) {
        Optional<Message> message = service.findByUuid(uuid);

        if(message.isEmpty()) {
            throw new NotResourceException("No content");
        }
        return ResponseEntity.ok(message.get());
    }

    @PostMapping("/user")
    public ResponseEntity<List<Message>> findByUserId(@RequestBody Long id) {
       return ResponseEntity.ok(service.findByUserId(id));
    }

    //@PreAuthorize("hasAnyRole('USER,ADMIN')")
    @PostMapping("/add")
    public ResponseEntity<Message> save(@RequestBody MessageDTO messageDTO) {

        /* if (userFeignClient.userExist(messageDTO.getUserId()).getBody() == null) {
            throw new NotResourceException("user is not exist");
        }*/

        messageDTO.setDate(new Date());

        /* if (messageDTO.getClientId() == null) {
            throw new NotValidateParamException("Missed param: clientId");
        }*/

        Message result = service.save(messageDTO);
        return ResponseEntity.ok(result);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping("/delete")
    public void delete(@RequestBody UUID uuid){
            /*Optional<Message> message = service.findByUuid(uuid);

            if(message.isEmpty()){
                throw new NotResourceException("No content for delete");
            }*/

            service.delete(uuid);
    }


    @PutMapping("/delete/client/id")
    public void deleteByClientId(@RequestBody UUID clientId){
        service.deleteByClientId(clientId);
    }

    @PutMapping("/update")
    public ResponseEntity<Message> update(@RequestBody MessageDTO messageDTO) {
            Optional<Message> message = service.findByUuidOrClientId(messageDTO.getUuid(),messageDTO.getClientId());

            if(message.isEmpty()) {
                throw new NotResourceException("No content for update");
            }
            if(messageDTO.getUuid() == null) {
                throw new NotValidateParamException("Missed param: id");
            }
            if (userFeignClient.userExist(messageDTO.getUserId()) == null) {
                throw new NotResourceException("user is not exist");
            }
            if(messageDTO.getUuid() == null && messageDTO.getClientId() != null){
                messageDTO.setUuid(message.get().getUuid());
            }

            return ResponseEntity.ok(service.update(messageDTO));

    }


    @PostMapping("/search")
    public ResponseEntity<List<Message>> findBySearch(@RequestBody MessageSearchDTO messageSearchDTO){

        UUID uuid = messageSearchDTO.getUuid() == null ? null :messageSearchDTO.getUuid();
        String valueMessage = messageSearchDTO.getValue() == null ? null :messageSearchDTO.getValue();
        Long userId = messageSearchDTO.getUserId() == null ? null : messageSearchDTO.getUserId();
        UUID chatId = messageSearchDTO.getChatId() == null ? null : messageSearchDTO.getChatId();

        Date dateStart = messageSearchDTO.getDateStart()  == null? null: messageSearchDTO.getDateStart();
        Date dateEnd = messageSearchDTO.getDateEnd() == null? null : messageSearchDTO.getDateEnd();

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

            dateStart = calendarStart.getTime(); // записываем конечную дату с 00:00

        }
        String sortDirection = messageSearchDTO.getSortDirection() == null ? "" : messageSearchDTO.getSortDirection();
        int pageNumber = messageSearchDTO.getPageNumber() == null ? 0 : messageSearchDTO.getPageNumber();
        int pageSize =  messageSearchDTO.getPageSize() == null ? 1 : messageSearchDTO.getPageSize();
        String sortColumn = messageSearchDTO.getSortColumn() == null? "id" :messageSearchDTO.getSortColumn();

        Sort.Direction direction = sortDirection.isEmpty() || sortDirection.equalsIgnoreCase("ASK") ?
                Sort.Direction.ASC : Sort.Direction.DESC;
        Sort sort = Sort.by(direction,sortColumn,COLUM_ID);
        PageRequest pageRequest = PageRequest.of(pageNumber,pageSize,sort);

        return ResponseEntity.ok(service.findBySearch(dateStart,dateEnd,valueMessage,uuid,userId,chatId,pageRequest).getContent());

    }
}

