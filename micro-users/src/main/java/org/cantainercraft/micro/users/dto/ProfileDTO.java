package org.cantainercraft.micro.users.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.cantainercraft.project.entity.users.Profile_Image;
import org.cantainercraft.project.entity.users.User;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
public class ProfileDTO{

    @NotNull(message = "Uuid is empty")
    private UUID uuid;

    @NotNull(message = "Uuid is empty")
    private Date sunsetTime;

    private List<Profile_Image> profileImages;

    @NotNull
    private String about;

    @NotNull
    private String telephone;

    @NotNull
    private User user;

}
