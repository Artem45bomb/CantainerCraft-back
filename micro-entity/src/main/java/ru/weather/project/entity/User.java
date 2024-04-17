package ru.weather.project.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "user_data",schema = "weather",catalog = "postgres")
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String name;

    private String password;

    private String email;

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof User user)) return false;
        return Objects.equals(getId(), user.getId());
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",schema = "weather",catalog = "postgres",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles;

    @ManyToMany
    @JoinTable(name = "user_subscription",schema = "weather",catalog = "postgres",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "subscription_id"))
    private List<Subscription> subscriptions;


    @OneToOne
    @JoinColumn(name = "profile_uuid",referencedColumnName = "uuid")
    private Profile profile;

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
