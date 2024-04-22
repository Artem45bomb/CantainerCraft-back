package org.cantainercraft.micro.chats.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Getter
@Setter
@ToString
public class UserChatSearchDTO {
    private Long id;
    private Long userId;
    private UUID chatId;
}
