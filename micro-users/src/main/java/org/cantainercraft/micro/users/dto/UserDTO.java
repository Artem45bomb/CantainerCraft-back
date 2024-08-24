package org.cantainercraft.micro.users.dto;

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
    @Size(min = 5, max = 50, message = "Имя пользователя должно содержать от 5 до 50 символов")
    @NotBlank(message = "Name is empty")
    private String username;
    @Size(min = 5,message = "Length must be greater than 5")
    @NotBlank(message = "Email is empty")
    private String email;
    @Size(min = 5,max = 255, message = "Длина пароля должна быть от 5 - 255 символов")
    @NotBlank(message = "Password is empty")
    private String password;
    private List<Role> roles;
    private List<Subscription> subscriptions;
}
