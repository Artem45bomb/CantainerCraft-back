package ru.project.socket.chats.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.weather.project.entity.Chat;
import ru.weather.project.entity.Message;
import ru.weather.project.entity.TypeChat;

import java.util.Date;
import java.util.List;
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
