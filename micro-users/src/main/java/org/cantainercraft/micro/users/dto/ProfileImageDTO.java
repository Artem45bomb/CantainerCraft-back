package org.cantainercraft.micro.users.dto;


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

    private String srcContent;

    private Profile profile;
}
