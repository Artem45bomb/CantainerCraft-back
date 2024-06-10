package org.containercraft.servicedbstore.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.UUID;


@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "content", schema = "messenger_chats")
public class Content implements Serializable {

    @Id
    private UUID uuid;

    private String srcContent;

    private String type;

    private BigInteger sizeByte;

    private UUID messageId;
}
