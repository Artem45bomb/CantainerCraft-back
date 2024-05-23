package org.cantainercraft.project.entity.chats;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Cacheable
@Table(name = "chat_settings_channel", schema = "messenger_chats", catalog = "micro_chats")
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Chat_Settings_Chanel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    private boolean singMessage;

    private String type_channel;

    @JsonIgnore
    @OneToOne(mappedBy = "chatSettingsChanel",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Chat_Settings chatSettings;

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Chat_Settings_Chanel that)) return false;
        return Objects.equals(getUuid(), that.getUuid()) && Objects.equals(getType_channel(), that.getType_channel());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUuid(), getType_channel());
    }
}
