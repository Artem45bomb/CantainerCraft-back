package org.containercraft.servicerealtime.dto;

import java.io.Serializable;
import java.util.UUID;

public record RoomServiceDTO  (
        Long userId,
        UUID roomId
){}
