package org.cantainercraft.project.entity.chats;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.util.UUID;

@Getter
@Setter
@Builder
@Entity
@Table(name = "chat_secured,",schema = "messenger_chats",catalog = "micro_chats")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@AllArgsConstructor
@NoArgsConstructor
public class Chat_Secured {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    @JoinColumn(name = "chat_id",unique = true,nullable = false)
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Chat chat;

    @Column(nullable = false)
    private Long userId;
}
