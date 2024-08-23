package org.cantainercraft.micro.chats.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.cantainercraft.project.entity.chats.Chat;
import org.cantainercraft.project.entity.chats.Message;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MessageReadDTO implements Serializable {
    private UUID uuid;
    @NotNull(message = "Message is empty")
    private Message message;
    @Min(value = 1,message = "User id must he greater than 0")
    private Long userId;
    private boolean isRead;
    @NotNull(message = "Chat is empty")
    private Chat chat;
}