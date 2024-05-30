package org.cantainercraft.micro.users.dto;


import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ProfileImageDTO {
    private UUID uuid;

   private String srcContent;

    private String profileId;
}
