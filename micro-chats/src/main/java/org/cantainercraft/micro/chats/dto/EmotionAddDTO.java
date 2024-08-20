package org.cantainercraft.micro.chats.dto;


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
    private UUID messageId;
    private UUID emotionId;
    private Long userId;
}
