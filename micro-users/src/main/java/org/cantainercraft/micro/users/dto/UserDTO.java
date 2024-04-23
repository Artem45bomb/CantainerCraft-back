package org.cantainercraft.micro.users.dto;

import lombok.Getter;
import lombok.Setter;
import org.cantainercraft.project.entity.Role;
import org.cantainercraft.project.entity.Subscription;

import java.util.List;

@Getter
@Setter
public class UserDTO {
    private Long id;
    private String name;
    private String email;
    private String password;
    private List<Role> roles;
    private List<Subscription> subscriptions;
}
