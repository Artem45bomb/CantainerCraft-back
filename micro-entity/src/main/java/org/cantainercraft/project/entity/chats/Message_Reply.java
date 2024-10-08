package org.cantainercraft.project.entity.chats;

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
@Table(name = "message_reply", schema = "messenger_chats", catalog = "micro_chats")
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class Message_Reply implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Message message;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "message_replyId", referencedColumnName = "uuid",nullable = false)
    private Message messageReply;

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Message_Reply that)) return false;
        return Objects.equals(getUuid(), that.getUuid());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUuid());
    }
}
