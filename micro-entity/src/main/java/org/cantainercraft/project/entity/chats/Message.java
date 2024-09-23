package org.cantainercraft.project.entity.chats;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Cacheable
@Table(name = "messages", schema = "messenger_chats", catalog = "micro_chats")
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class Message implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    private String text;

    @Column(name="is_pinned")
    private Boolean isPinned;

    @Enumerated(EnumType.STRING)
    private TypeMessage type;

    private Date date;

    private Long userId;

    @OneToMany(mappedBy = "message",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Message_Content> contents;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chat_id", referencedColumnName = "uuid")
    private Chat chat;

    @OneToMany(mappedBy = "message")
    private List<User_Emotion> userEmotions;

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Message message)) return false;
        return Objects.equals(getUuid(), message.getUuid());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUuid());
    }
}
