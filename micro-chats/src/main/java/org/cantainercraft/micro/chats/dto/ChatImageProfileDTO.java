package org.cantainercraft.micro.chats.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.cantainercraft.project.entity.chats.Chat;
import java.io.Serializable;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatImageProfileDTO implements Serializable {
    private UUID uuid;
    @NotBlank(message = "Src content is empty")
    private String srcContent;
    @NotBlank(message = "Chat is empty")
    private Chat chat;
}
