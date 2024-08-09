package org.cantainercraft.micro.chats.repository.dto;

import lombok.Builder;
import lombok.Data;
import org.cantainercraft.project.entity.chats.Message;

@Data
@Builder
public class MessageRabbitDTO {
    private String actionType;
    private Message message;
}
