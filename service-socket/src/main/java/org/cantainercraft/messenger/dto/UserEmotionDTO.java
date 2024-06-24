package org.cantainercraft.messenger.dto;

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
public class UserEmotionDTO implements Serializable {
    private UUID uuid;
    private MessageDTO message;
    private EmotionDTO emotion;
    private Long userId;
}
