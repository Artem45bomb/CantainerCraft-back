package org.cantainercraft.micro.users.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.cantainercraft.project.entity.users.User;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class UserOnlineDTO {

    private UUID uuid;
    @NotNull(message = "User is empty")
    private User user;

    private boolean is_online;
}
