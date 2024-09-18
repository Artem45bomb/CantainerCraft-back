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
@Table(name = "message_forward", schema = "messenger_chats", catalog = "micro_chats")
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class Message_Forward implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "message_from_id")
    private Message messageFrom;

    @ManyToOne(fetch = FetchType.EAGER)
    private Message message;

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Message_Forward that)) return false;
        return Objects.equals(getUuid(), that.getUuid());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUuid());
    }
}
