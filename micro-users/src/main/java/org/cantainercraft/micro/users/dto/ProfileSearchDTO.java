package org.cantainercraft.micro.users.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfileSearchDTO {
    private Long userId;
    private String email;
}
