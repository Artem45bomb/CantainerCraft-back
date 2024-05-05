package org.cantainercraft.micro.users.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ProfileSearchDTO {
    private Long userId;
    private String email;
}
