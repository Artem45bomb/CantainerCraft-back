package org.cantainercraft.micro.chats.service.impl;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MessageEmotionDTO {
    private UUID messageClientId;
    private UUID emotionId;
}
