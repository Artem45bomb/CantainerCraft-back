package ru.micro.chats.data.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.weather.project.entity.chats.Message;
import ru.weather.project.entity.TypeChat;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChatDTO {
    private String name;
    private Date date;
    private TypeChat typeChat;
    private List<Message> messages;

}
