package org.cantainercraft.project.entity.chats;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.io.Serializable;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
@Entity
@Table(name = "user_chat",schema = "messenger_chats",catalog = "micro_chats")
@ToString
public class User_Chat implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "chat_id",referencedColumnName = "uuid")
    private Chat chat;
}
