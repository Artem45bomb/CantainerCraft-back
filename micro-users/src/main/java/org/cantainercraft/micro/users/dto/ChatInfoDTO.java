package org.cantainercraft.micro.users.dto;

import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChatInfoDTO {

    private UUID uuid;

    @NotNull(message = "Chat id is empty")
    private UUID chatId;

    private boolean is_secured;

    private boolean is_notification;

}
