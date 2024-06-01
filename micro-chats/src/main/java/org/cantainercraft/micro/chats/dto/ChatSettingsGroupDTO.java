package org.cantainercraft.micro.chats.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChatSettingsGroupDTO {
    private UUID settingGroupId;
    private String typeGroup;
    private boolean isSendMessage;
    private boolean isSendPhoto;
    private boolean isSendVideo;
    private boolean isSendMusic;
    private boolean isSendVoice;
    private boolean isSendFile;
    private boolean isStikerGif;
    private boolean isCreateSurvey;
    private boolean isPreviewSrc;
    private boolean isAddUsers;
    private boolean isCreateTheme;
    private boolean isSecuredMessage;
    private boolean isUpdateMessage;
    private Time time;
}
