package org.cantainercraft.micro.chats.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.cantainercraft.project.entity.chats.Chat;

@Getter
@Setter
@ToString
public class UserChatDTO {

    private Long id;

    private Long userId;

    private Chat chat;
}
