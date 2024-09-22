package org.cantainercraft.micro.chats.dto;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmotionAddDTO implements Serializable {
    private UUID uuid;
    @NotNull(message="Message id is empty")
    private UUID messageId;
    @NotNull(message="Emotion id id is empty")
    private UUID emotionId;
    @Min(value=1,message="User id must be greater than 0")
    @NotNull(message="User id is empty")
    private Long userId;
}
