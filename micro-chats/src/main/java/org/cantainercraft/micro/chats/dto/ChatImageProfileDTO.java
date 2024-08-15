package org.cantainercraft.micro.chats.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.cantainercraft.project.entity.chats.Chat;
import org.cantainercraft.project.entity.chats.Content;

import java.io.Serializable;
import java.util.UUID;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChatImageProfileDTO implements Serializable {
    private UUID uuid;
    private Content content;
    private Chat chat;
}
