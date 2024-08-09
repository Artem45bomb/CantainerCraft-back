package org.cantainercraft.micro.chats.repository.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.cantainercraft.project.entity.chats.Chat;
import org.cantainercraft.project.entity.chats.Message;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MessageReadDTO implements Serializable {
    private UUID uuid;
    private Message message;
    private Long userId;
    private boolean isRead;
    private Chat chat;
}