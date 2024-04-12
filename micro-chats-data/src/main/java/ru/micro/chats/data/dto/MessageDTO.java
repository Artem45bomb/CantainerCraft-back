package ru.micro.chats.data.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.weather.project.entity.chats.Chat;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MessageDTO {

    private String value;

    private Date date;

    private UUID recipientId;

    private Long userId;

    public Chat chat;

}
