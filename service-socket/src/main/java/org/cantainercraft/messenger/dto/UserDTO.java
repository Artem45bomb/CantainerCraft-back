package org.cantainercraft.messenger.dto;

import lombok.Data;
import lombok.Getter;

import java.util.List;

@Data
public class UserDTO {
    private Long id;
    private String name;
    private String password;
    private String email;

    private List<SubscriptionDTO> subscription;
    private List<RoleDTO> roles;

    private ProfileDTO profile;

}
