package org.containercraft.servicedbstore.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "emotions",schema = "messenger_chats")
public class Emotion implements Serializable {

    @Id
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
