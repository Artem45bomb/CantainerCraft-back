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
@Table(name = "chat_settings_channel", schema = "messenger_chats", catalog = "micro_chats")
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Chat_Settings_Chanel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    private boolean singMessage;

    private String type_channel;

    @JsonIgnore
    @OneToOne(mappedBy = "chatSettingChanel",fetch = FetchType.LAZY)
    private Chat_Settings chatSettings;
}
