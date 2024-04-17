package ru.micro.chats.data.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ru.weather.project.entity.chats.Chat;

@Getter
@Setter
@ToString
public class UserChatDTO {

    private Long id;

    private Long userId;

    private Chat chat;
}
