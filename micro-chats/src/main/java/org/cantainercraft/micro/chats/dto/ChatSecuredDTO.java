package org.cantainercraft.micro.chats.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChatSecuredDTO implements Serializable {
    private UUID uuid;
    @Min(value = 1,message = "User id must be greater than 0")
    private Long userId;
    @NotNull(message = "Chat id is empty")
    private UUID chatId;
}
