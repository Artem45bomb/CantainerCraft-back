package org.cantainercraft.project.entity.chats;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.cantainercraft.project.entity.TypeChat;

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

    public Chat(UUID uuid, String srcImage, String name, Date date, TypeChat typeChat) {
        this.uuid = uuid;
        this.srcImage = srcImage;
        this.name = name;
        this.date = date;
        this.typeChat = typeChat;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    @Column(name = "src_image")
    private String srcImage;

    @Column(name = "chat_name")
    private String name;

    @Column(name = "create_date",updatable = false)
    private Date date;

    private TypeChat typeChat;

    @OneToMany(mappedBy = "chat",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private List<User_Chat> users;

    @OneToMany(mappedBy = "chat",fetch = FetchType.EAGER)
    private List<Message> messages;
}


