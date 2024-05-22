package org.cantainercraft.micro.users.convertor;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.cantainercraft.micro.users.dto.ProfileDTO;
import org.cantainercraft.project.entity.users.Profile;

@Component
@RequiredArgsConstructor
public class ProfileDTOConvertor {

    private final ModelMapper modelMapper;

    public ProfileDTO convertProfileToProfileDTO(Profile profile){
        return modelMapper.map(profile,ProfileDTO.class);
    }

    public Profile convertProfileDTOToProfile(ProfileDTO profileDTO){
        return modelMapper.map(profileDTO,Profile.class);
    }
}
