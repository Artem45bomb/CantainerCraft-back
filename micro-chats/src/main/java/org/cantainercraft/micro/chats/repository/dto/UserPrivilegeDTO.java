package org.cantainercraft.micro.chats.repository.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.cantainercraft.project.entity.chats.Privilege;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserPrivilegeDTO implements Serializable {
    private UUID uuid;
    private Long userId;
    private Privilege privilege;
}