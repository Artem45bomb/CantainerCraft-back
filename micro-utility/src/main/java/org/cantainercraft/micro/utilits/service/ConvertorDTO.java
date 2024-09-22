package org.cantainercraft.micro.utilits.service;

@Deprecated
public interface ConvertorDTO<DTO,Object> {
    DTO convertEntityToDTO(Object object);

    Object convertDTOToEntity(DTO dto);
}
