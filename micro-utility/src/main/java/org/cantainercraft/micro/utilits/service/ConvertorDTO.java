package org.cantainercraft.micro.utilits.service;

public interface ConvertorDTO<DTO,Object> {
    DTO convertEntityToDTO(Object object);

    Object convertDTOToEntity(DTO dto);
}
