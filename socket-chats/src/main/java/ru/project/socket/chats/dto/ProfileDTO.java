package ru.project.socket.chats.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
public class ProfileDTO {
    private UUID uuid;

    private String telephone;

    private Date sunsetTime;

    private String srcImage;

    private String about;
}
