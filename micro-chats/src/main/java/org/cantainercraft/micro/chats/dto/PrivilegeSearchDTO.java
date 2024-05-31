package org.cantainercraft.micro.chats.dto;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class PrivilegeSearchDTO {
    private UUID chatId;
    private String chatName;
}
