package ru.javabegin.backend.todo.eurekaclient.dto;

import lombok.Getter;
import lombok.Setter;
import ru.javabegin.backend.todo.eurekaclient.entity.Role;
import ru.javabegin.backend.todo.eurekaclient.entity.Subscription;

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
