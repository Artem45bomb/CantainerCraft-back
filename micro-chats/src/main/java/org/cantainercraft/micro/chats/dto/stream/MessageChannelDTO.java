package org.cantainercraft.micro.chats.dto.stream;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.cantainercraft.micro.chats.dto.ChatDTO;
import org.cantainercraft.project.entity.chats.Message_Content;
import org.cantainercraft.project.entity.chats.User_Emotion;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MessageChannelDTO implements Serializable {
    private UUID uuid;
    private String text;
    private String type;
    private Date date;
    private boolean isPinned;
    private Long userId;
    public ChatDTO chat;
}
