package org.cantainercraft.micro.users.convertor;

import lombok.RequiredArgsConstructor;
import org.cantainercraft.micro.utilits.service.ConvertorDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.cantainercraft.micro.users.dto.ProfileDTO;
import org.cantainercraft.project.entity.users.Profile;

@Component
@RequiredArgsConstructor
public class ProfileDTOConvertor implements ConvertorDTO<ProfileDTO,Profile> {

    private final ModelMapper modelMapper;

    @Override
    public ProfileDTO convertEntityToDTO(Profile profile) {
        return modelMapper.map(profile,ProfileDTO.class);
    }

    @Override
    public Profile convertDTOToEntity(ProfileDTO profileDTO) {
        return modelMapper.map(profileDTO,Profile.class);
    }
}
