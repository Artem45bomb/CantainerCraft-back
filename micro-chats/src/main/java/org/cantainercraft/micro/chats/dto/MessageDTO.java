package org.cantainercraft.micro.chats.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.cantainercraft.project.entity.chats.Message_Content;
import org.cantainercraft.project.entity.chats.User_Emotion;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MessageDTO {
    private UUID uuid;

    private String text;
    @NotBlank(message = "Type is empty")
    private String type;
    @NotNull(message = "Data id is empty")
    private Date date;
    private boolean isPinned;
    @Min(value = 1,message = "User id must be greater than 0")
    private Long userId;
    @NotNull(message = "Chat is empty")
    public ChatDTO chat;
    private List<User_Emotion> userEmotions;
    private List<Message_Content> contents;
}
