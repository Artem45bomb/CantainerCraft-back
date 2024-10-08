package org.cantainercraft.micro.users.dto;

import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSearchDTO {
    @Email
    private String email;
    private String password;
}
