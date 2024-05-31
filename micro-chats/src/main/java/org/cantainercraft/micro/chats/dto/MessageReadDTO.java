package org.cantainercraft.micro.chats.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MessageReadDTO {
    private UUID uuid;
    private UUID messageId;
    private Long userId;
    private boolean isRead;
    private UUID chatId;
}