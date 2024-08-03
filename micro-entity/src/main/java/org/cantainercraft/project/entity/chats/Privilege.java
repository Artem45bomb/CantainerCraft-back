package org.cantainercraft.project.entity.chats;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CacheConcurrencyStrategy;

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
@Table(name = "privilege", schema = "messenger_chats", catalog = "micro_chats")
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Privilege {

    @Id
    @GeneratedValue(strategy =GenerationType.UUID)
    private UUID uuid;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Chat chat;

    private String nameRole;

    @OneToMany(mappedBy = "privilege",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private List<User_Privilege> userPrivileges;

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Privilege privilege)) return false;
        return Objects.equals(getUuid(), privilege.getUuid()) && Objects.equals(getNameRole(), privilege.getNameRole());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUuid(), getNameRole());
    }
}
