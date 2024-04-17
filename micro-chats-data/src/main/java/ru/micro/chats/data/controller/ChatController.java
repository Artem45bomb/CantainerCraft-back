package ru.micro.chats.data.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.micro.chats.data.dto.ChatDTO;
import ru.micro.chats.data.dto.ChatSearchDTO;
import ru.micro.chats.data.service.ChatService;
import ru.micro.chats.data.dto.ChatUpdateDTO;
import ru.weather.project.entity.TypeChat;
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
        return new ResponseEntity("No content", HttpStatus.NO_CONTENT);
    }

    @PostMapping("/name")
    public ResponseEntity<Chat> findByName(@RequestBody String name){
        Optional<Chat> chat = chatService.findByName(name);


        if(chat.isPresent()) {
            return ResponseEntity.ok(chat.get());
        }
        return new ResponseEntity("No content", HttpStatus.NO_CONTENT);
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

    @PostMapping("/add")
    public ResponseEntity<Chat> save(@RequestBody ChatDTO chatDTO){
        Optional<Chat> chat = chatService.findByName(chatDTO.getName());

        if(chat.isPresent()){
            return new ResponseEntity("chat is exist",HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok(chatService.save(chatDTO));
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

    @PostMapping
    public ResponseEntity<List<Chat>> findBySearch(@RequestBody ChatSearchDTO chatSearchDTO){

        UUID uuid = chatSearchDTO.getUuid() == null ? null :chatSearchDTO.getUuid();
        String chatName = chatSearchDTO.getChatName() == null ? null : chatSearchDTO.getChatName();
        TypeChat typeChat = chatSearchDTO.getTypeChat() == null ? null : chatSearchDTO.getTypeChat();

        Date dateStart = null;
        Date dateEnd = null;

        if (chatSearchDTO.getDateEnd() != null) {

            Calendar calendarEnd = Calendar.getInstance();
            calendarEnd.setTime(chatSearchDTO.getDateEnd());
            calendarEnd.set(Calendar.HOUR_OF_DAY, 23);
            calendarEnd.set(Calendar.MINUTE, 59);
            calendarEnd.set(Calendar.SECOND, 59);
            calendarEnd.set(Calendar.MILLISECOND, 999);

            dateEnd = calendarEnd.getTime(); // записываем конечную дату с 23:59
        }

        if (chatSearchDTO.getDateStart() != null) {

            Calendar calendarStart = Calendar.getInstance();
            calendarStart.setTime(chatSearchDTO.getDateEnd());
            calendarStart.set(Calendar.HOUR_OF_DAY, 0);
            calendarStart.set(Calendar.MINUTE, 0);
            calendarStart.set(Calendar.SECOND, 0);
            calendarStart.set(Calendar.MILLISECOND, 0);

            dateEnd = calendarStart.getTime(); // записываем конечную дату с 23:59
        }

        return ResponseEntity.ok(chatService.findBySearch(uuid,chatName,typeChat,dateStart,dateEnd));

    }

}