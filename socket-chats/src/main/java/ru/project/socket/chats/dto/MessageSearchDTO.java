package ru.project.socket.chats.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MessageSearchDTO {
    private UUID uuid;
    private Date dateStart;
    private Date dateEnd;
    private String value;
    private Long userId;
}
