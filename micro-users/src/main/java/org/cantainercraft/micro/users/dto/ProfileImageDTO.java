package org.cantainercraft.micro.users.dto;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.cantainercraft.project.entity.users.Profile;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProfileImageDTO {
    private UUID uuid;

    @NotNull(message = "Src content is empty")
    private String srcContent;

    @NotNull(message = "Profile is empty")
    private Profile profile;
}
