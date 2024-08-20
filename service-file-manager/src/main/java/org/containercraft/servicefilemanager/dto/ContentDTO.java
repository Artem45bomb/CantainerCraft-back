package org.containercraft.servicefilemanager.dto;

import lombok.*;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ContentDTO implements Serializable {
    private UUID uuid;
    private String srcContent;
    private String type;
    private long sizeByte;
    private boolean isDelete;
}
