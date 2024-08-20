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
@Table(name = "profile_image",schema = "messenger_users",catalog = "postgres")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Profile_Image implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    @Column(nullable = false)
    private String srcContent;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Profile profile;

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Profile_Image that)) return false;
        return Objects.equals(getUuid(), that.getUuid());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUuid());
    }
}
