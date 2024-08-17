package org.cantainercraft.micro.chats.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChatSecuredDTO {
    private UUID uuid;
    private Long userId;
    private UUID chatId;
}
