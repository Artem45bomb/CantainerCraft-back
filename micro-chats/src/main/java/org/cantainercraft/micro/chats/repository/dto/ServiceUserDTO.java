package org.cantainercraft.micro.chats.repository.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ServiceUserDTO implements Serializable {
    private String username;
    private String password;
}
