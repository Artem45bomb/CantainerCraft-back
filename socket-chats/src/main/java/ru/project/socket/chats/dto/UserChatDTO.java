package ru.project.socket.chats.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserChatDTO {
    private Long id;
    private Long userId;
    private ChatDTO chatDTO;
}
