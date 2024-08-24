package org.cantainercraft.micro.users.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
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
