package org.cantainercraft.messenger.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserAddChatDTO {
    private Long id;
    private Long userId;
    private ChatDTO chat;
}
