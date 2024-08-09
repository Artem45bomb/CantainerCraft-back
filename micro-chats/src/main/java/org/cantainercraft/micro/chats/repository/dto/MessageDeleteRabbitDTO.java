package org.cantainercraft.micro.chats.repository.dto;


import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class MessageDeleteRabbitDTO {
    private String actionType;
    private UUID uuid;
}
