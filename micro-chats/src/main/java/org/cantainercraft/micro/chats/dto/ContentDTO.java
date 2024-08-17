package org.cantainercraft.micro.chats.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.cantainercraft.project.entity.chats.Message;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ContentDTO implements Serializable {
    private UUID uuid;
    private String srcContent;
    private String type;
    private BigInteger sizeByte;
    private Message message;
}
