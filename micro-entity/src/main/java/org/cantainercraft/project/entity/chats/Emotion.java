package org.cantainercraft.project.entity.chats;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Getter
@Setter
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "emotions",schema = "messenger_chats",catalog = "micro_chats")
public class Emotion implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    private String unicode;


    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Emotion emotion)) return false;
        return Objects.equals(getUuid(), emotion.getUuid()) && Objects.equals(getUnicode(), emotion.getUnicode());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUuid(), getUnicode());
    }
}
