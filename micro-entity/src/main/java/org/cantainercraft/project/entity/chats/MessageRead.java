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
@Table(name = "message_read",schema = "messenger_chats",catalog = "micro_chats")
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class MessageRead implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    @ManyToOne
    private Message message;

    private Long userId;

    private boolean is_read;

    @JsonIgnore
    @ManyToOne
    private Chat chat;

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof MessageRead that)) return false;
        return Objects.equals(getUuid(), that.getUuid()) && Objects.equals(getUserId(), that.getUserId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUuid(), getUserId());
    }
}
