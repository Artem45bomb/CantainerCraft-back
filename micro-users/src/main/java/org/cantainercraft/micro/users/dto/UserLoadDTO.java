package org.cantainercraft.micro.users.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.cantainercraft.project.entity.users.Role;
import org.cantainercraft.project.entity.users.Subscription;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserLoadDTO implements Serializable {
    private Long id;
    private String username;
    private String email;
    private List<Role> roles;
    private List<Subscription> subscriptions;
}
