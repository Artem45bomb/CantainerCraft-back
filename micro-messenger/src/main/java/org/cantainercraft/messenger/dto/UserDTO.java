package org.cantainercraft.messenger.dto;

import java.util.List;

public class UserDTO {
    private Long id;
    private String name;
    private String password;
    private String email;

    private List<SubscriptionDTO> subscription;
    private List<RoleDTO> roles;

    private ProfileDTO profile;

}
