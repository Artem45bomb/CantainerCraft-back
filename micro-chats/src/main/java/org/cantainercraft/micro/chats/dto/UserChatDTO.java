package org.cantainercraft.micro.chats.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
public class UserChatDTO {

    private Long id;

    private Long userId;

    private ChatDTO chat;
}
