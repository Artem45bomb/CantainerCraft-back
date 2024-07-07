package org.cantainercraft.messenger.dto;


import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class MessageForwardDTO {
    private UUID messageForwardId;
    private MessageDTO messageDTO;
}
