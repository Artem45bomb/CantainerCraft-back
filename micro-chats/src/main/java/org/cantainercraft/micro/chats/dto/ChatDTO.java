package org.cantainercraft.micro.chats.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.cantainercraft.project.entity.chats.Message;
import org.cantainercraft.project.entity.users.TypeChat;
import org.cantainercraft.project.entity.chats.User_Chat;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChatDTO {
    private UUID uuid;
    private String name;
    private Date date;
    private TypeChat typeChat;
    private List<Message> messages;
    private List<User_Chat> users;
}
