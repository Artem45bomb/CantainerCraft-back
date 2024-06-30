package org.containercraft.servicerealtime.service.impl;

import lombok.RequiredArgsConstructor;
import org.cantainercraft.micro.utilits.exception.NotResourceException;
import org.cantainercraft.micro.utilits.exception.NotValidateParamException;
import org.containercraft.servicerealtime.convertor.RoomDTOConvertor;
import org.containercraft.servicerealtime.dto.RoomDTO;
import org.containercraft.servicerealtime.entity.Room;
import org.containercraft.servicerealtime.repository.RoomRepository;
import org.containercraft.servicerealtime.service.RoomService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {
    private final RoomRepository repository;
    private final RoomDTOConvertor convertor;

    @Override
    public List<Room> findAll() {
        return repository.findAll();
    }

    @Override
    public Room findById(UUID roomId) {
        Optional<Room> room = repository.findById(roomId);

        if(room.isEmpty()) throw new NotResourceException("room is not exist");

        return room.get();
    }

    @Override
    public Room save(RoomDTO dto) {
        if(dto.uuid() != null) throw new NotValidateParamException("missed param: uuid");

        Room room = convertor.convertDTOToEntity(dto);
        return repository.save(room);
    }

    @Override
    public Room update(RoomDTO dto) {
        if(!repository.existsById(dto.uuid()))  throw new NotResourceException("room is not exist");

        Room room = convertor.convertDTOToEntity(dto);
        return repository.save(room);
    }

    @Override
    public void deleteById(UUID roomId) {
        if(!repository.existsById(roomId))  throw new NotResourceException("room is not exist");

        repository.deleteById(roomId);
    }

    @Override
    public boolean existRoom(UUID roomId) {
        return repository.existsById(roomId);
    }
}
