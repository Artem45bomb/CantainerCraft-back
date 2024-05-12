package org.cantainercraft.project.entity.chats;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CacheConcurrencyStrategy;


import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.UUID;


@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Cacheable
@Table(name = "messages",schema = "messenger_chats",catalog = "micro_chats")
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Message implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    private String value;

    private Date date;


    private Long userId;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chat_uuid",referencedColumnName = "uuid")
    private Chat chat;

    @ManyToMany
    @JoinTable(name = "messages_emotions",schema = "messenger_chats",catalog = "micro_chats"
    ,joinColumns = @JoinColumn(name="message_id"),
    inverseJoinColumns = @JoinColumn(name = "emotion_id"))
    private List<Emotion> emotions;

}
