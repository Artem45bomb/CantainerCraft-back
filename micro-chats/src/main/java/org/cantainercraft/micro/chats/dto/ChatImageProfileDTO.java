package org.cantainercraft.micro.chats.dto;

import lombok.*;
import org.cantainercraft.project.entity.chats.Chat;

import java.io.Serializable;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatImageProfileDTO implements Serializable {
    private UUID uuid;
    private String srcContent;
    private Chat chat;
}
