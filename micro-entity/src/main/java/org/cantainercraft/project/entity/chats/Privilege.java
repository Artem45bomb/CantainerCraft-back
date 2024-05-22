package org.cantainercraft.project.entity.chats;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.util.List;
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
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Chat chat;

    private String name_role;

    @OneToMany(mappedBy = "privilege",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private List<User_Privilege> userPrivileges;
}
