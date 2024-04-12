package ru.micro.chats.data.dto;

import lombok.Getter;
import lombok.Setter;
import ru.weather.project.entity.TypeChat;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
public class ChatSearchDTO {
    UUID uuid;
    String chatName ;
    Date dateStart;
    Date dateEnd;
    TypeChat typeChat;
}
