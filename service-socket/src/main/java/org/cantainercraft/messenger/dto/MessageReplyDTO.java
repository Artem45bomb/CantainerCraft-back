package org.cantainercraft.messenger.dto;


import lombok.*;

import java.util.UUID;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MessageReplyDTO {
    private UUID messageReplyId;
    private MessageDTO messageDTO;
}
