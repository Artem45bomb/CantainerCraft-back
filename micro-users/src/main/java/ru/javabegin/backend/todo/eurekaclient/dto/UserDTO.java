package ru.javabegin.backend.todo.eurekaclient.dto;

import lombok.Getter;
import lombok.Setter;
import ru.weather.project.entity.Role;
import ru.weather.project.entity.Subscription;

import java.util.List;

@Getter
@Setter
public class UserDTO {
    private String name;
    private String email;
    private String password;
    private List<Role> roles;
    private List<Subscription> subscriptions;
}
