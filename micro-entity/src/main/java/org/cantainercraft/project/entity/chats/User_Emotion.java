package org.cantainercraft.project.entity.chats;


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
    @ManyToOne(cascade = CascadeType.ALL)
    private Message message;
    @ManyToOne(cascade = CascadeType.ALL)
    private Emotion emotion;
    private Long userId;
}
