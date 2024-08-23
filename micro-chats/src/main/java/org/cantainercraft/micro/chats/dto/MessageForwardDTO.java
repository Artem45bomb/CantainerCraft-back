package org.cantainercraft.micro.chats.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.cantainercraft.project.entity.chats.Message;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MessageForwardDTO implements Serializable {
    private UUID uuid;
    @NotNull(message = "MessageFrom is empty")
    private Message messageFrom;
    @NotNull(message = "Message is empty")
    private Message message;
}

