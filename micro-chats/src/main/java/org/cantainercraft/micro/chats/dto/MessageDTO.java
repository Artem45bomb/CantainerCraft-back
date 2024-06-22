package org.cantainercraft.micro.chats.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MessageDTO {
    private UUID uuid;

    private String text;

    private Date date;

    private Boolean isPinned;


    private Long userId;

    public ChatDTO chat;

    private UUID clientId;

    private List<EmotionDTO> emotions;
}
