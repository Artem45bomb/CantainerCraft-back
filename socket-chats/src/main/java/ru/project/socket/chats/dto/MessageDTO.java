package ru.project.socket.chats.dto;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.util.UUID;


@Getter
@Setter
public class MessageDTO {
    private UUID uuid;
    private ChatDTO chat;
    private String value;
    private Long userId;
    private LocalDate localDate;

}
