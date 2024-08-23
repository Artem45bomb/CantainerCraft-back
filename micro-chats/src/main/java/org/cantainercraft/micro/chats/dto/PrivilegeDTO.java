package org.cantainercraft.micro.chats.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.cantainercraft.project.entity.chats.Chat;
import org.cantainercraft.project.entity.chats.User_Privilege;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Data
public class PrivilegeDTO implements Serializable {
    private UUID uuid;
    @NotNull(message = "Chat is empty")
    private Chat chat;
    @Min(value = 1,message = "Name role length must be greater than 0")
    @NotBlank(message = "MessageFrom is empty")
    private String nameRole;
    private List<User_Privilege> userPrivilege;
}