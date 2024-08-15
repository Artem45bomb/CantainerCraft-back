package org.cantainercraft.micro.chats.dto;


import lombok.*;
import org.cantainercraft.project.entity.chats.Chat;
import org.cantainercraft.project.entity.chats.Chat_Settings;
import org.cantainercraft.project.entity.chats.Chat_Settings_Chanel;
import org.cantainercraft.project.entity.chats.Chat_Settings_Group;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChatSettingsDTO implements Serializable {
    private UUID uuid;
    private boolean isAllowTheme;
    private Chat_Settings_Group chatSettingsGroup;
    private Chat_Settings_Chanel chatSettingsChanel;
    private Chat chat;
}

