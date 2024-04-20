package ru.project.socket.chats.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserAddChatDTO {
    private Long id;
    private Long userId;
    private ChatDTO chat;
}
