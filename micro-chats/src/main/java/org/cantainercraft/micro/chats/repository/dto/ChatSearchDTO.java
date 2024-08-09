package org.cantainercraft.micro.chats.repository.dto;

import lombok.Getter;
import lombok.Setter;
import org.cantainercraft.project.entity.users.TypeChat;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
public class ChatSearchDTO  implements Serializable {
    UUID uuid;
    String chatName ;
    Date dateStart;
    Date dateEnd;
    TypeChat typeChat;
}
