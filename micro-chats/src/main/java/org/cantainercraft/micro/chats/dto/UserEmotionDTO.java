package org.cantainercraft.micro.chats.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.cantainercraft.project.entity.chats.Emotion;
import org.cantainercraft.project.entity.chats.Message;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserEmotionDTO implements Serializable {
    private UUID uuid;
    @NotNull(message = "Message is empty")
    private Message message;
    @NotNull(message = "Emotion is empty")
    private Emotion emotion;
    @Min(value = 1,message = "User id must be greater than 0")
    @NotNull(message = "User id is empty")
    private Long userId;
}
