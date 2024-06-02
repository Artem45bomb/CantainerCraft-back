package org.cantainercraft.micro.chats.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.cantainercraft.project.entity.chats.Chat_Settings;

import java.time.Duration;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChatSettingsGroupDTO {
    private UUID settingGroupId;
    private UUID uuid;
    private String typeGroup;
    private boolean isSendMessage;
    private boolean isSendPhoto;
    private boolean isSendVide;
    private boolean isSendMusic;
    private boolean isSendVoice;
    private boolean isSendFile;
    private boolean isStikerGif;
    private boolean isCreateSurvey;
    private boolean isPreviewSrc;
    private boolean isUpdateMessage;
    private Duration slowMode;

    private Chat_Settings chatSettings;
}
