package org.cantainercraft.micro.chats.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmotionAddDTO implements Serializable {
    private UUID messageClientId;
    private UUID emotionId;
    private Long userId;
}
