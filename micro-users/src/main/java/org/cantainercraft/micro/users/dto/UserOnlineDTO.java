package org.cantainercraft.micro.users.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserOnlineDTO {

    private int id;

    private UserDTO user;

    private boolean is_online;

}
