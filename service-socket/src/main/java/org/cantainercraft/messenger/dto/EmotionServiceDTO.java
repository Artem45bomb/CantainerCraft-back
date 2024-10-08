package org.cantainercraft.messenger.dto;

import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EmotionServiceDTO implements Serializable {
    UUID uuid;
    UUID messageClientId;
    UUID emotionId;
    Long userId;
}
