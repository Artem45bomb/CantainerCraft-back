package org.cantainercraft.micro.chats.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cantainercraft.micro.chats.repository.dto.ChatDTO;
import org.cantainercraft.micro.chats.repository.dto.ChatSearchDTO;
import org.cantainercraft.micro.chats.service.ChatService;
import org.cantainercraft.micro.chats.service.UserChatService;
import org.cantainercraft.micro.utilits.exception.NotResourceException;
import org.cantainercraft.micro.utilits.exception.NotValidateParamException;
import org.cantainercraft.project.entity.users.TypeChat;
import org.cantainercraft.project.entity.chats.Chat;
import org.cantainercraft.project.entity.chats.User_Chat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/chat")
@Slf4j
@RequiredArgsConstructor
public class ChatController {
    private final ChatService service;
    private final UserChatService userChatService;


    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("/all")
    public ResponseEntity<List<Chat>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @PostMapping("/uuid")
    public ResponseEntity<Chat> findByUUID(@RequestBody UUID uuid){
        Optional<Chat> chat = service.findByUUID(uuid);

        if(chat.isEmpty()){
            throw new NotResourceException("No content");
        }

        return ResponseEntity.ok(chat.get());
    }

    @PutMapping("/delete")
    public void delete(@RequestBody UUID uuid){
        service.delete(uuid);
    }


    @PostMapping("/add")
    public ResponseEntity<Chat> save(@RequestBody ChatDTO chatDTO){
        return ResponseEntity.ok(service.save(chatDTO));
    }

    @PutMapping("/update")
    public ResponseEntity<Chat> update(@RequestBody ChatDTO chatDTO) throws Exception{
        if(chatDTO.getUuid() == null){
            throw new NotValidateParamException("missed param: id");
        }

        return ResponseEntity.ok(service.update(chatDTO));
    }


    @PostMapping("/search")
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

            dateStart = calendarStart.getTime(); // записываем конечную дату с 23:59
        }

        return ResponseEntity.ok(service.findBySearch(uuid,chatName,typeChat,dateStart,dateEnd));

    }

    //ищет пользователей по userId через userChatService так как все пользователи хранятся в user_chat
    @PostMapping("/user/search")
    public ResponseEntity<List<Chat>> search(@RequestBody Long userId){
        List<User_Chat> userChats =userChatService.findBySearch(userId,null);
        
        return ResponseEntity.ok(userChats.stream()
                .map(User_Chat::getChat)
                .collect(Collectors.toList()));
    }

}