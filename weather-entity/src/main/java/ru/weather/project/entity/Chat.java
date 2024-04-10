package ru.weather.project.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.util.ArrayList;
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
@Table(name = "chats",schema = "messenger",catalog = "tg_clone_messenger")
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    @Column(name = "chat_name")
    private String name;

    @Column(name = "create_date",updatable = false)
    private Date date;

    private TypeChat typeChat;


    @OneToMany(mappedBy = "chat",fetch = FetchType.EAGER)
    private List<Message> messages;
}


