package org.cantainercraft.project.entity.chats;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
@Table(name = "user_emotion",schema = "messenger_chats",catalog = "micro_chats")
public class User_Emotion implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    private Message message;
    @ManyToOne(optional = false)
    private Emotion emotion;
    private Long userId;
}
