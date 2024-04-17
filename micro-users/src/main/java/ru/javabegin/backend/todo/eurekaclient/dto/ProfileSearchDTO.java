package ru.javabegin.backend.todo.eurekaclient.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfileSearchDTO {
    private Long userId;
    private String email;
}
