package org.cantainercraft.project.entity.chats;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.time.Duration;
import java.util.Objects;
import java.util.UUID;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Cacheable
@Table(name = "chat_settings_group", schema = "messenger_chats", catalog = "micro_chats")
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Chat_Settings_Group {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
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

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Chat_Settings_Group that)) return false;
        return Objects.equals(getUuid(), that.getUuid());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUuid());
    }
}
