package org.cantainercraft.project.entity.chats;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.cantainercraft.project.entity.users.TypeChat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;
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

    public Chat(UUID uuid, String name, Date date, TypeChat typeChat) {
        this.uuid = uuid;
        this.name = name;
        this.date = date;
        this.typeChat = typeChat;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    @OneToMany(mappedBy = "chat",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Chat_Image_Profile> chatImageProfiles;

    @Column(name = "chat_name")
    private String name;

    @Column(name = "create_date",updatable = false)
    private Date date;

    @Column(nullable = false)
    private TypeChat typeChat;

    @OneToMany(mappedBy = "chat",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private List<User_Chat> users;

    @JsonIgnore
    @OneToMany(mappedBy = "chat",fetch = FetchType.LAZY)
    private List<Message> messages;

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Chat chat)) return false;
        return Objects.equals(getUuid(), chat.getUuid());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUuid());
    }
}


