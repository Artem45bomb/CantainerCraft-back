package org.cantainercraft.project.entity.chats;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Cacheable
@Builder
@Entity
@Table(name = "chat_image_profile", schema = "messenger_chats", catalog = "micro_chats")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Chat_Image_Profile implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    private String srcContent;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Chat chat;

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Chat_Image_Profile that)) return false;
        return Objects.equals(getUuid(), that.getUuid()) && Objects.equals(getSrcContent(), that.getSrcContent());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUuid(), getSrcContent());
    }
}
