package org.cantainercraft.project.entity.chats;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.io.Serializable;
import java.math.BigInteger;
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
@Table(name = "content", schema = "messenger_chats", catalog = "micro_chats")
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Content implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    @Column(unique = true,updatable = false)
    private String srcContent;

    @Column(updatable = false)
    private String type;

    private BigInteger sizeByte;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Message message;

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Content content)) return false;
        return Objects.equals(getUuid(), content.getUuid()) && Objects.equals(getSrcContent(), content.getSrcContent()) && Objects.equals(getType(), content.getType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUuid(), getSrcContent(), getType());
    }
}
