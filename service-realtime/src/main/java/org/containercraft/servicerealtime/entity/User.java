package org.containercraft.servicerealtime.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.io.Serializable;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "user_room",schema = "realtime_service",catalog = "postgres")
public class User implements Serializable {
    @Id
    private Long id;
    private boolean isOne;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Room room;
}
