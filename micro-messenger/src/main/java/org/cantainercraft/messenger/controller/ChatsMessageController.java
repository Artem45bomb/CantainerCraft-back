package org.cantainercraft.messenger.controller;

import org.cantainercraft.messenger.dto.ChatDTO;
import org.cantainercraft.messenger.dto.UserChatDTO;
import org.cantainercraft.messenger.exception.NotExist;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.cantainercraft.messenger.service.ChatService;

import java.util.UUID;

@Controller
public class ChatsMessageController {

    private final ChatService chatService;

    public ChatsMessageController(ChatService chatService) {
        this.chatService = chatService;
    }


    @MessageMapping("/chat/add")
    @SendTo("/client/createChat")
    private ChatDTO createChat(@Payload ChatDTO chatDTO){

        //генерация uuid
        UUID uuid = UUID.randomUUID();
        chatDTO.setUuid(uuid);

        //create new chat
        //создания происходит асинхронно и не блокирует главный поток
        chatService.saveAsync(chatDTO).subscribe(System.out::println);

        return chatDTO;

    }

    @MessageMapping("/chat/delete")
    @SendTo("/client/deleteChat")
    private UUID deleteChat(@Payload UUID uuid){

        //удаляем чат
        chatService.deleteAsync(uuid).subscribe(System.out::println);

        return uuid;
    }

    @MessageMapping("/chat/update")
    @SendTo("/client/updateChat")
    private ChatDTO updateChat(@Payload ChatDTO chatDTO){

        //обновляем чат
        chatService.saveAsync(chatDTO).subscribe(System.out::println);

        return chatDTO;
    }

    @MessageMapping("/user/add")
    @SendTo("/client/addUser")
    private UserChatDTO addUserChat(UserChatDTO message){
        
        Long userId = message.getUserId();
        ChatDTO chatDTO = message.getChatDTO();
        
        if(userId == null){
            throw new NotExist("missed param: userId");
        }
        
        if(chatDTO == null){
            throw new NotExist("missed param: chatId");
        }
        
        chatService.addUser(message).subscribe(System.out::println);
        
        return message;
    }
    
    @MessageMapping("/user/delete")
    @SendTo("/client/deleteUser")
    private boolean deleteUser(UserChatDTO message){

        Long userId = message.getUserId();
        ChatDTO chatDTO = message.getChatDTO();

        if(userId == null){
            throw new NotExist("missed param: userId");
        }

        if(chatDTO == null){
            throw new NotExist("missed param: chatId");
        }

        chatService.deleteUser(message).subscribe(System.out::println);

        return true;
    }
}
