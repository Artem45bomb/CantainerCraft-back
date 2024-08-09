package org.cantainercraft.micro.chats.repository.dto.stream;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MessageChannelDTO implements Serializable {
    private UUID uuid;

    private String text;

    private Date date;

    private Boolean isPinned;

    private Long userId;

    //public UUID chatId;

    private UUID clientId;
}
