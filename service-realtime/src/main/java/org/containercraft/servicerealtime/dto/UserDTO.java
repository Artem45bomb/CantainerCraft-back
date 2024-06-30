package org.containercraft.servicerealtime.dto;


public record UserDTO
        (Long id,
         boolean isOne,
         RoomDTO room)
{}
