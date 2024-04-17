package ru.project.socket.chats.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import ru.project.socket.chats.dto.ChatDTO;
import ru.project.socket.chats.service.ChatService;

import java.util.UUID;

@Controller
public class ChatsMessageController {

    private final ChatService chatService;

    public ChatsMessageController(ChatService chatService) {
        this.chatService = chatService;
    }


    @MessageMapping("/chat/add")
    @SendTo("/createChat")
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
    @SendTo("/deleteChat")
    private UUID deleteChat(@Payload UUID uuid){

        //удаляем чат
        chatService.deleteAsync(uuid).subscribe(System.out::println);

        return uuid;
    }

    @MessageMapping("/chat/update")
    @SendTo("/updateChat")
    private ChatDTO updateChat(@Payload ChatDTO chatDTO){

        //обновляем чат
        chatService.saveAsync(chatDTO).subscribe(System.out::println);

        return chatDTO;
    }

}
