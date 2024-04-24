package org.cantainercraft.project.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.io.Serializable;
import java.util.Date;
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

    @Column(name="src_image_profile")
    private String srcImage;

    private String about;

    @OneToOne
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    private User user;

}
