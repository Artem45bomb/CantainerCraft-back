package org.cantainercraft.micro.chats.convertor;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.cantainercraft.micro.chats.repository.dto.PrivilegeDTO;
import org.cantainercraft.project.entity.chats.Privilege;

@Component
@RequiredArgsConstructor
public class PrivilegeDTOConvertor {
    private final ModelMapper modelMapper;

    public PrivilegeDTO convertEntityToDTO(Privilege privilege) {
        return modelMapper.map(privilege, PrivilegeDTO.class);
    }

    public Privilege convertDTOToEntity(PrivilegeDTO dto) {
        return modelMapper.map(dto, Privilege.class);
    }
}