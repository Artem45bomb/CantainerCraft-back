package ru.javabegin.backend.todo.eurekaclient.dto;

import lombok.Getter;
import lombok.Setter;
import ru.weather.project.entity.User;

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
