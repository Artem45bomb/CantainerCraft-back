package org.cantainercraft.micro.users.dto;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@AllArgsConstructor
public class ProfileSearchDTO {
    private Long userId;
    @Email
    private String email;
}
