package org.cantainercraft.micro.chats.dto;


import jakarta.validation.constraints.NotNull;
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
public class EmotionDeleteDTO implements Serializable {
    @NotNull(message = "Uuid is empty")
    private UUID uuid;
}
