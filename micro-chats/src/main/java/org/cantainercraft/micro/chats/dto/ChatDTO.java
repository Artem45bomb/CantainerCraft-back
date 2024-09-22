package org.cantainercraft.micro.chats.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.cantainercraft.project.entity.users.TypeChat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatDTO implements Serializable {
    private UUID uuid;
    @NotBlank(message = "Name is empty")
    private String name;
//    @FutureOrPresent(message = "Data is not valid")
    private Date date;
    @NotBlank(message = "Link is empty")
    private String link;
    private TypeChat typeChat;
    private List<MessageDTO> messages;
    private List<UserChatDTO> users;
}
