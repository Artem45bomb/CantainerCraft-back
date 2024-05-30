package org.cantainercraft.micro.utilits.service;

public interface ConvertorDTO <DTO,Entity> {
    DTO convertEntityToDTO(Entity entity);

    Entity convertDTOToEntity(DTO dto);
}
