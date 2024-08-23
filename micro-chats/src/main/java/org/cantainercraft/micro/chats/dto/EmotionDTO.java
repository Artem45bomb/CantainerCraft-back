package org.cantainercraft.micro.chats.dto;

import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmotionDTO implements Serializable {
    @Min(value = 1,message = "Unicode length must be greater than 0")
    private String unicode;
    private UUID uuid;
}
