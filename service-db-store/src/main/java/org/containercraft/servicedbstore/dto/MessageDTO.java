package org.containercraft.servicedbstore.dto;

import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class MessageDTO implements Serializable {
    private UUID uuid;

    private String text;

    private Date date;

    private Boolean isPinned;


    private Long userId;

    //private UUID chatId;

    private UUID clientId;

}
