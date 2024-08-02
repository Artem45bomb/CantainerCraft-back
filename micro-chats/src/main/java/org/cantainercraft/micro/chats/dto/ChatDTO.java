package org.cantainercraft.micro.chats.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChatDTO implements Serializable {
    private UUID uuid;
    private String name;
    private Date date;
    private String link;
    private String typeChat;
    private List<MessageDTO> messages;
    private List<UserChatDTO> users;
}
