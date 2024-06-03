package org.cantainercraft.micro.chats.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.cantainercraft.project.entity.chats.Privilege;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserPrivilegeDTO {
    private UUID uuid;
    private Long userId;
    private Privilege privilege;
}