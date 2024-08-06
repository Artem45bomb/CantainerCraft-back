package org.cantainercraft.micro.users.dto;

import lombok.Getter;
import lombok.Setter;
import org.cantainercraft.project.entity.users.Role;
import org.cantainercraft.project.entity.users.Subscription;

import java.util.List;

@Getter
@Setter
public class UserClientDTO {
    private Long id;
    private String username;
    private String email;
    private List<Role> roles;
    private List<Subscription> subscriptions;
}
