package org.cantainercraft.micro.utilits.service;

public interface ConvertorDTO <DTO,Entity> {
    DTO convertorEntityToDTO(Entity entity);

    Entity convertorDTOToEntity(DTO dto);
}
