package org.cantainercraft.micro.users.dto;

import lombok.Getter;
import lombok.Setter;
import org.cantainercraft.project.entity.users.User;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
public class ProfileDTO{
    private UUID uuid;

    private Date sunsetTime;

    private String srcImage;

    private String about;

    private String telephone;

    private User user;

}
