package org.cantainercraft.project.entity.chats;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Cacheable
@Builder
@Entity
@Table(name = "message_content", schema = "messenger_chats", catalog = "micro_chats")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Message_Content {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String srcContent;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Message message;

}
