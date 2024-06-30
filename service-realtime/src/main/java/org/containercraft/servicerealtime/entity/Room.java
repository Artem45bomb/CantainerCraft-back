package org.containercraft.servicerealtime.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Builder
@Entity
@Table(name = "room_data",schema = "realtime_service",catalog = "postgres")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@NoArgsConstructor
@AllArgsConstructor
public class Room implements Serializable {
    @Id
    private UUID uuid;


    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL,mappedBy = "room")
    private Set<User> users;
}
