package org.cantainercraft.project.entity.chats;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CacheConcurrencyStrategy;

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
public class Chat_Settings {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    private boolean isAllowTheme;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Chat_Settings_Group chatSettingsGroup;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Chat_Settings_Chanel chatSettingsChanel;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    private Chat chat;
}
