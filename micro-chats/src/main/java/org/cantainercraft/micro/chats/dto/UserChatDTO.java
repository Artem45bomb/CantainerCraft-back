package org.cantainercraft.micro.chats.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;


@Getter
@Setter
@ToString
public class UserChatDTO implements Serializable {

    private Long id;
    @Min(value = 1,message = "User id must be greater than 0")
    private Long userId;
    @NotNull(message = "Chat id is empty")
    private ChatDTO chat;
}
