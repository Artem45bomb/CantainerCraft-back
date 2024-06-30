package org.containercraft.servicerealtime.convertor;

import lombok.RequiredArgsConstructor;
import org.cantainercraft.micro.utilits.service.ConvertorDTO;
import org.containercraft.servicerealtime.dto.RoomDTO;
import org.containercraft.servicerealtime.entity.Room;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RoomDTOConvertor implements ConvertorDTO<RoomDTO, Room> {
    private final ModelMapper mapper;

    @Override
    public RoomDTO convertEntityToDTO(Room room) {
        return mapper.map(room,RoomDTO.class);
    }

    @Override
    public Room convertDTOToEntity(RoomDTO dto) {
        return mapper.map(dto,Room.class);
    }
}
