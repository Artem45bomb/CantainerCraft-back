package org.containercraft.servicedbstore.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "chats",schema = "messenger_chats")
public class Chat implements Serializable {
    @Id
    private UUID uuid;

    @Column("chat_name")
    private String name;

    @Column("create_date")
    private Date date;

    private TypeChat typeChat;
    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Chat chat)) return false;
        return Objects.equals(getUuid(), chat.getUuid());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUuid());
    }
}


