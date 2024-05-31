package org.cantainercraft.micro.chats.dto;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class PrivilegeDTO {
    private UUID uuid;
    private String nameRole;
    private UUID chatId;
    private UUID userPrivilegeId;
}