package org.cantainercraft.micro.users.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.cantainercraft.project.entity.users.Role;
import org.cantainercraft.project.entity.users.Subscription;

import java.util.List;

@Getter
@Setter
public class UserClientDTO {
    private Long id;
    @Size(min = 5, max = 50, message = "Length must be greater than 5")
    @NotBlank(message = "Name is empty")
    private String username;
    @Email
    @Size(min = 5,message = "Length must be greater than 5")
    @NotBlank(message = "Email is empty")
    private String email;
    private String password;
    private List<Role> roles;
    private List<Subscription> subscriptions;
}
