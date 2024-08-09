package org.cantainercraft.micro.chats.repository.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;


@Getter
@Setter
@ToString
public class UserChatDTO implements Serializable {

    private Long id;

    private Long userId;

    private ChatDTO chat;
}
