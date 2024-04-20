package ru.weather.project.entity.chats;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import ru.weather.project.entity.TypeChat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Cacheable
@Table(name = "chats",schema = "messenger_chats",catalog = "micro_chats")
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class Chat implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    @Column(name = "chat_name")
    private String name;

    @Column(name = "create_date",updatable = false)
    private Date date;

    private TypeChat typeChat;

    @OneToMany(mappedBy = "chat",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private List<User_Chat> userChat;

    @OneToMany(mappedBy = "chat",fetch = FetchType.EAGER)
    private List<Message> messages;
}


