package org.containercraft.servicerealtime.service;

import org.containercraft.servicerealtime.dto.RoomDTO;
import org.containercraft.servicerealtime.entity.Room;

import java.util.List;
import java.util.UUID;

public interface RoomService {
    List<Room> findAll();

    Room findById(UUID roomId);

    Room save(RoomDTO dto);

    Room update(RoomDTO dto);

    void deleteById(UUID roomId);

    boolean existRoom(UUID roomId);
}
