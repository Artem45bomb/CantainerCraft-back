package org.cantainercraft.micro.chats.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChatSettingsDTO {
    private UUID uuid;
    private boolean isAllowTheme;
    private UUID settingGroupId;
    private UUID settingChanelId;
    private UUID chatId;
    private int userId;
}
