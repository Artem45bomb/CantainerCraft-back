package org.cantainercraft.micro.chats.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.cantainercraft.project.entity.chats.Emotion;
import org.cantainercraft.project.entity.chats.Message;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserEmotionDTO implements Serializable {
    private UUID uuid;
    private Message message;
    private Emotion emotion;
    private Long userId;
}
