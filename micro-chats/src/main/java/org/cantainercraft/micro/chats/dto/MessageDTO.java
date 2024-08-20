package org.cantainercraft.micro.chats.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.cantainercraft.project.entity.chats.Message_Content;
import org.cantainercraft.project.entity.chats.User_Emotion;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MessageDTO {
    private UUID uuid;
    private String text;
    private String type;
    private Date date;
    private Boolean isPinned;
    private Long userId;
    public ChatDTO chat;
    private List<User_Emotion> userEmotions;
    private List<Message_Content> contents;
}
