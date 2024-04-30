package org.cantainercraft.project.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @Column(name = "username",unique = true,nullable = false)
    private String username;

    @Column(name = "password",nullable = false)
    private String password;

    @Column(name = "email",unique = true,nullable = false)
    private String email;

    public User(Long id,String name,String password,String email){
        this.username = name;
        this.password =password;
        this.email = email;
        this.id =id;
    }

    public User(String name,String password){
        this.username = name;
        this.password =password;
    }

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


    @JsonIgnore
    @OneToOne(mappedBy = "user",fetch = FetchType.LAZY)
    private Profile profile;

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "name = " + username + ", " +
                "password = " + password + ", " +
                "email = " + email + ", " +
                "profile = " + profile + ")";
    }
}
