package org.cantainercraft.project.entity.users;


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
@ToString
@Table(name = "user_online",schema = "messenger_users",catalog = "postgres")
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class User_Online implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    @JsonIgnore
    @JoinColumn(name = "user_id",updatable = false,nullable = false)
    @OneToOne(fetch = FetchType.LAZY,optional = false)
    private User user;

    private boolean is_online;

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof User_Online that)) return false;
        return Objects.equals(getUuid(), that.getUuid()) && getUser().getId().equals(that.getUser().getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUuid());
    }
}
