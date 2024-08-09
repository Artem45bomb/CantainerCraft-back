package org.cantainercraft.micro.chats.repository.dto;

import lombok.Data;
import org.cantainercraft.project.entity.chats.Chat;
import org.cantainercraft.project.entity.chats.User_Privilege;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Data
public class PrivilegeDTO implements Serializable {
    private UUID uuid;
    private Chat chat;
    private String nameRole;
    private List<User_Privilege> userPrivilege;
}