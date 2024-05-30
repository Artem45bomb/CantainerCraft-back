package org.cantainercraft.micro.users.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ChatInfoDTO {

    private UUID uuid;

    private int userId;

    private int chatId;

    private boolean is_secured;

    private boolean is_notification;

}
