package org.cantainercraft.micro.chats.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@ToString
public class UserChatSearchDTO implements Serializable {
    private Long userId;
    private UUID chatId;
}
