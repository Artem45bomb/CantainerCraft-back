package ru.project.socket.chats.dto;

import lombok.Getter;
import lombok.Setter;


import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
public class ChatDTO {
    private UUID uuid;
    private String name;
    private LocalDate date;
    private TypeChat typeChat;

}

enum TypeChat{
    GROUP,PERSONALLY
}