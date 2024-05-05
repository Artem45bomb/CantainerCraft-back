package org.cantainercraft.messenger.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;


@Getter
@Setter
public class MessageDTO {
    private UUID uuid;
    private ChatDTO chat;
    private String value;
    private Long userId;
    private Date date;

}
