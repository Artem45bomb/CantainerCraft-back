package org.cantainercraft.micro.users.dto;


import jakarta.validation.constraints.*;
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

    @Size(min = 3,message = "Min length 3 symbols")
    @NotBlank(message = "Src content is empty")
    @NotEmpty
    private String srcContent;
    @NotNull(message = "Profile is empty")
    private Profile profile;
}
