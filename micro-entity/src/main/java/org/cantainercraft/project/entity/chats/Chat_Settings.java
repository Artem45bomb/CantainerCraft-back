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
@Table(name = "chat_settings", schema = "messenger_chats", catalog = "micro_chats")
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Chat_Settings implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    private boolean isAllowTheme;

    @Column(updatable = false)
    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER,optional = false)
    private Chat_Settings_Group chatSettingsGroup;

    @Column(updatable = false)
    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER,optional = false)
    private Chat_Settings_Chanel chatSettingsChanel;

    @JsonIgnore
    @Column(updatable = false)
    @OneToOne(fetch = FetchType.LAZY,optional = false)
    private Chat chat;

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Chat_Settings that)) return false;
        return Objects.equals(getUuid(), that.getUuid());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUuid());
    }
}
