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
public class MessageReplyDTO implements Serializable {
    private UUID uuid;
    @NotNull(message = "MessageReply is empty")
    private Message messageReply;
    @NotNull(message = "Message is empty")
    private MessageDTO message;
}
