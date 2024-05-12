package org.cantainercraft.micro.chats.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.cantainercraft.project.entity.chats.Message;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmotionDTO {
    private String unicode;
    private UUID uuid;
    private List<MessageDTO> messages;

}
