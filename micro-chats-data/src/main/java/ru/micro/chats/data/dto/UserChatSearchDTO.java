package ru.micro.chats.data.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ru.weather.project.entity.chats.Chat;

import java.util.UUID;

@Getter
@Setter
@ToString
public class UserChatSearchDTO {
    private Long id;
    private Long userId;
    private UUID chatId;
}
