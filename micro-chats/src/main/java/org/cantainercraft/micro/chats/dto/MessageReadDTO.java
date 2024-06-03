package org.cantainercraft.micro.chats.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.cantainercraft.project.entity.chats.Chat;
import org.cantainercraft.project.entity.chats.Message;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MessageReadDTO {
    private UUID uuid;
    private Message message;
    private Long userId;
    private boolean isRead;
    private Chat chat;
}