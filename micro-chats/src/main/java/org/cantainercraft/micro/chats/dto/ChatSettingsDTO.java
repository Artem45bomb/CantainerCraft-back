package org.cantainercraft.micro.chats.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.cantainercraft.project.entity.chats.Chat;
import org.cantainercraft.project.entity.chats.Chat_Settings;
import org.cantainercraft.project.entity.chats.Chat_Settings_Chanel;
import org.cantainercraft.project.entity.chats.Chat_Settings_Group;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChatSettingsDTO {
    private UUID uuid;
    private boolean isAllowTheme;
    private Chat_Settings_Group chatSettingsGroup;
    private Chat_Settings_Chanel chatSettingsChanel;
    private Chat chat;
}
