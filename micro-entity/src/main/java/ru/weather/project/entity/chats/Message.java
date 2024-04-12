package ru.weather.project.entity.chats;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CacheConcurrencyStrategy;


import java.util.Date;
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
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    private String value;

    private Date date;

    private UUID recipientId;

    private Long userId;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chat_uuid",referencedColumnName = "uuid")
    public Chat chat;
}
