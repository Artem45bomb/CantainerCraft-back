package org.cantainercraft.micro.chats.repository.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Data
public class PrivilegeSearchDTO implements Serializable {
    private UUID chatId;
}
