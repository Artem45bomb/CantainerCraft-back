package org.cantainercraft.messenger.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserChatDTO {
    private Long id;
    private Long userId;
    private ChatDTO chatDTO;
}
