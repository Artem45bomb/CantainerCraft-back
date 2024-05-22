package org.cantainercraft.project.entity.users;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "profile_settings",schema = "weather",catalog = "postgres")
@Builder
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class Profile implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    @Column(name="number_telephone")
    private String telephone;

    @Column(name="sunset_time")
    private Date sunsetTime;

    @OneToMany(mappedBy = "profile",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Profile_Image> profileImages;

    @Column(name = "about_user")
    private String about;

    @OneToOne
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    private User user;

}
