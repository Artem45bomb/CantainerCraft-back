package org.cantainercraft.micro.chats.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.cantainercraft.project.entity.chats.Privilege;

import java.io.Serializable;
import java.sql.ConnectionBuilder;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserPrivilegeDTO implements Serializable {
    private UUID uuid;
    @Min(value=1,message="User id must be greater than 0")
    @NotNull(message="User id is empty")
    private Long userId;
    @NotNull(message="Privilege id is empty")
    private Privilege privilege;

}