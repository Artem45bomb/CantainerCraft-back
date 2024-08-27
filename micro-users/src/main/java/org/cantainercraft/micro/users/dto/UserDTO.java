package org.cantainercraft.micro.users.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.cantainercraft.project.entity.users.Role;
import org.cantainercraft.project.entity.users.Subscription;

import java.util.List;

@Getter
@Setter
public class UserDTO {
    private Long id;
    @Size(min = 5, max = 50, message = "Length must be greater than 5")
    @NotBlank(message = "Name is empty")
    private String username;
    @Email
    @Size(min = 5,message = "Length must be greater than 5")
    @NotBlank(message = "Email is empty")
    private String email;
    @Size(min = 5,max = 255, message = "The line length must be between 5 and 255 characters")
    @NotBlank(message = "Password is empty")
    private String password;
    private List<Role> roles;
    private List<Subscription> subscriptions;
}
