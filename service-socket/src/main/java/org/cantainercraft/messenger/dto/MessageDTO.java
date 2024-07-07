package org.cantainercraft.messenger.dto;

import lombok.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
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
